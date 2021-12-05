package com.example.djsongrequestsbusiness.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.djsongrequestsbusiness.data.repositories.UserRepository
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception
import java.lang.IllegalArgumentException

class ForgotPasswordViewModel(application: Application): AndroidViewModel(application) {


    private val auth = FirebaseAuth.getInstance()
    private val userRepository = UserRepository(auth)

    private val _setPasswordResetNotify = MutableLiveData<String>()
    val setPasswordResetNotify: LiveData<String>
        get() = _setPasswordResetNotify

    private val _setEmailError = MutableLiveData<String>()
    val setEmailError: LiveData<String>
        get() = _setEmailError

    fun onClickReset(email: String) {

        if (email.isNullOrEmpty()){
            _setEmailError.value = "An email address must be provided."
        }else {
            userRepository.passwordReset(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _setPasswordResetNotify.value = "Instructions for password reset have been sent to your email."
                }else {
                    _setEmailError.value = "Sorry, this is not a valid email in our system. Please enter a valid email and try again."
                }
            }
        }
    }
}