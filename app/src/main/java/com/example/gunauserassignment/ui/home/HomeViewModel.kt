package com.example.gunauserassignment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gunauserassignment.data.User
import com.google.firebase.Firebase

class HomeViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    init {
        // Fetch user data from Firebase and update _users LiveData
        // You can use Firebase Firestore, Realtime Database, or other methods here
        // Example:
//         Firebas.firestore.collection("users").addSnapshotListener { snapshot, error ->
//             val userList = snapshot?.toObjects(User::class.java)
//             _users.value = userList
//         }
    }
}
