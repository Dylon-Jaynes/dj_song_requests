package com.example.djsongrequestsbusiness.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.djsongrequestsbusiness.Event
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val authStatusMessage = MutableLiveData<Event<String>>()
    private val context by lazy { getApplication<Application>().applicationContext }

    fun onClickSignIn() {

    }
}