package com.example.djsongrequestsbusiness.ui.viewModels

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import com.example.djsongrequestsbusiness.data.dataClasses.LoginModel
import com.example.djsongrequestsbusiness.data.repositories.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class BasicInfoViewModel(application: Application): AndroidViewModel(application) {


    private val auth = FirebaseAuth.getInstance()
    private lateinit var authStatusMessage: MutableLiveData<String>
    fun onClickSignUp(loginModel: LoginModel) {
        val userRepository = UserRepository(auth)
        val userSignUpResult = userRepository.userSignUp(loginModel.email, loginModel.password)
        getAuthStatusMessage(userSignUpResult)
    }

    private fun getAuthStatusMessage(result: Task<AuthResult>) {
        result.addOnCompleteListener { result ->
            if (result.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(ContentValues.TAG, "createUserWithEmail:success")
                authStatusMessage.value = "Your account has been successfully created!"
            } else {
                // If sign in fails, display a message to the user.
                Log.w(ContentValues.TAG, "createUserWithEmail:failure", result.exception)
                authStatusMessage.value = "User account creation failed."
//                binding.edittextEmail.error = "This email address is already in use by another account."
            }
        }
    }

    fun userSignOut() {
        auth.signOut()
    }
}