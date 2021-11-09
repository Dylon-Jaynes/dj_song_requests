package com.example.djsongrequestsbusiness.ui.viewModels

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.djsongrequestsbusiness.Event
import com.example.djsongrequestsbusiness.data.dataClasses.LoginModel
import com.example.djsongrequestsbusiness.data.repositories.UserRepository
import com.example.djsongrequestsbusiness.utils.EspressoIdlingResource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import java.lang.IllegalArgumentException

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val _navigateToSongListFrag = MutableLiveData<Event<Boolean>>()
    val navigateToSongListFrag: LiveData<Event<Boolean>>
        get() = _navigateToSongListFrag

    private val _displayUserLoginResult = MutableLiveData<Event<String>>()
    val displayUserLoginResult: LiveData<Event<String>>
        get() = _displayUserLoginResult

    private val _setEmailError = MutableLiveData<String>()
    val setEmailError: LiveData<String>
        get() = _setEmailError

    private val _setPasswordError = MutableLiveData<String>()
    val setPasswordError: LiveData<String>
        get() = _setPasswordError

    private val auth = FirebaseAuth.getInstance()

    fun onClickLogin(loginCredentials: LoginModel) {
        val userRepository = UserRepository(auth)

        try {
            val userSignUpResult = userRepository.userLogin(loginCredentials.email, loginCredentials.password)
            getAuthStatusMessage(userSignUpResult)
        }catch (e: IllegalArgumentException){
            if (loginCredentials.email.isNullOrEmpty()){
                _setEmailError.value = "An email address must be provided."
            }

            if (loginCredentials.password.isNullOrEmpty()){
                _setPasswordError.value = "A password must be provided."
            }
        }
    }

    private fun getAuthStatusMessage(result: Task<AuthResult>) {
        result.addOnCompleteListener { result ->
            if (result.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(ContentValues.TAG, "createUserWithEmail:success")
                _displayUserLoginResult.value = Event("Login Successful!")
                _navigateToSongListFrag.value = Event(true)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(ContentValues.TAG, "createUserWithEmail:failure")
                _displayUserLoginResult.value = Event("Login failed.")
                try {
                    throw result.exception as FirebaseAuthException
                } catch (e: FirebaseAuthException) {
                    when (e.errorCode) {
                        "ERROR_INVALID_EMAIL" -> _setEmailError.value = "Please enter a valid email."
                        "ERROR_WEAK_PASSWORD" -> _setPasswordError.value = e.message
                    }
                }
            }
            EspressoIdlingResource.decrement()
        }
    }
}