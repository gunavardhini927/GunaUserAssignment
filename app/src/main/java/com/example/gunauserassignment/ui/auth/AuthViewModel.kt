package com.example.gunauserassignment.ui.auth


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gunauserassignment.data.AuthRepository
import com.example.gunauserassignment.data.Resource
import com.example.gunauserassignment.data.User
import com.example.gunauserassignment.data.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    private val _isPasswordVisible = MutableStateFlow(false)
    val isPasswordVisible: StateFlow<Boolean> = _isPasswordVisible

    fun togglePasswordVisibility() {
        _isPasswordVisible.value = !_isPasswordVisible.value
    }


//    private val _listOfUsers = MutableStateFlow<List<User>?>(null)
//    val listOfUsersFlow: StateFlow<List<User>?> = _listOfUsers


//    private val auth = FirebaseAuth.getInstance()
//    private val firestore = FirebaseFirestore.getInstance()


    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if(repository.currentUser != null){
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }

    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = repository.login(email, password)
        _loginFlow.value = result
    }

//    fun getAllUsers() = viewModelScope.launch {
//        _loginFlow.value = Resource.Loading
//        val result = repository.getAllUsers()
//        _listOfUsers.value = result
//    }

    fun signup(name: String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading
        val result = repository.signup(name, email, password)
        _signupFlow.value = result
    }

    fun logout() {
        repository.logout()
        _loginFlow.value = null
        _signupFlow.value = null
    }
}