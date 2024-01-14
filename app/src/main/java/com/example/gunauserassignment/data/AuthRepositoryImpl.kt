package com.example.gunauserassignment.data

import com.example.gunauserassignment.data.utils.await

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    private val auth = FirebaseAuth.getInstance()

    override suspend fun signup(
        name: String,
        email: String,
        password: String
    ): Resource<FirebaseUser> {
        return try{
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await();
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await();

            Resource.Success(result.user!!)
        }catch (e: Exception){
            e.printStackTrace();
            Resource.Failure(e);
        }
    }

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        return try{
            val result = firebaseAuth.signInWithEmailAndPassword(email,password).await();
            Resource.Success(result.user!!)
        }catch (e: Exception){
            e.printStackTrace();
            Resource.Failure(e);
        }
    }

    override fun logout() {
      firebaseAuth.signOut()
    }
//
//    override suspend fun getAllUsers(): List<User> {
//        return try {
//            val querySnapshot: QuerySnapshot =
//                firestore.collection("users").get().await()
//
//            querySnapshot.toObjects(User::class.java)
//        } catch (e: Exception) {
//            emptyList()
//        }
//    }
//

}