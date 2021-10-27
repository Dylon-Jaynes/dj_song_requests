package com.example.djsongrequestsbusiness.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import com.example.djsongrequestsbusiness.Event
import com.example.djsongrequestsbusiness.data.dataClasses.DjModel
import com.example.djsongrequestsbusiness.data.dataClasses.LoginModel
import com.example.djsongrequestsbusiness.data.repositories.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class BasicInfoViewModel(application: Application): AndroidViewModel(application) {


    private val auth = FirebaseAuth.getInstance()
    private val authStatusMessage = MutableLiveData<Event<String>>()
    private val context by lazy { getApplication<Application>().applicationContext }

    fun onClickSignUp(loginModel: LoginModel): Task<AuthResult> {
        val userRepository = UserRepository(auth)
        return userRepository.userSignUp(loginModel.email, loginModel.password)
    }

    fun userSignOut() {
        auth.signOut()
    }
}