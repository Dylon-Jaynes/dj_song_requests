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
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import java.lang.IllegalArgumentException

class BasicInfoViewModel(application: Application): AndroidViewModel(application) {

    private val _navigateToDjIdFrag = MutableLiveData<Event<Boolean>>()
    val navigateToDjIdFrag: LiveData<Event<Boolean>>
        get() = _navigateToDjIdFrag

    private val _displayCreateUserResult = MutableLiveData<Event<String>>()
    val displayCreateUserResult: LiveData<Event<String>>
        get() = _displayCreateUserResult

    private val _setEmailError = MutableLiveData<String>()
    val setEmailError: LiveData<String>
        get() = _setEmailError

    private val _setPasswordError = MutableLiveData<String>()
    val setPasswordError: LiveData<String>
        get() = _setPasswordError

    private val auth = FirebaseAuth.getInstance()

    fun onClickSignUp(loginModel: LoginModel) {
        val userRepository = UserRepository(auth)
        try {
            val userSignUpResult = userRepository.userSignUp(loginModel.email, loginModel.password)
            getAuthStatusMessage(userSignUpResult)
        }catch (e: IllegalArgumentException){
            if (loginModel.email.isNullOrEmpty()){
                _setEmailError.value = "An email address must be provided."
            }

            if (loginModel.password.isNullOrEmpty()){
                _setPasswordError.value = "A password must be provided."
            }
        }
    }

    private fun getAuthStatusMessage(result: Task<AuthResult>) {
        result.addOnCompleteListener { result ->
            if (result.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(ContentValues.TAG, "createUserWithEmail:success")
                _displayCreateUserResult.value = Event("Your account has been successfully created!")
                _navigateToDjIdFrag.value = Event(true)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(ContentValues.TAG, "createUserWithEmail:failure")
                _displayCreateUserResult.value = Event("User account creation failed.")
                    try {
                        throw result.exception as FirebaseAuthException
                    } catch (e: FirebaseAuthException) {
                        when (e.errorCode) {
                            "ERROR_EMAIL_ALREADY_IN_USE" -> _setEmailError.value = e.message
                            "ERROR_INVALID_EMAIL" -> _setEmailError.value = "Please enter a valid email."
                            "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" -> _setPasswordError.value = e.message
                            "ERROR_WEAK_PASSWORD" -> _setPasswordError.value = e.message
                        }
                    }
            }
        }
    }

    fun userSignOut() {
        auth.signOut()
    }
}
