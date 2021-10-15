package com.example.djsongrequestsbusiness.ui.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.djsongrequestsbusiness.Event
import com.example.djsongrequestsbusiness.data.dataClasses.DjModel
import com.example.djsongrequestsbusiness.data.repositories.FirebaseRepo
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DjIdViewModel : ViewModel() {

    private val auth = Firebase.auth
    private val authStatusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
        get() = authStatusMessage

    fun createAccount(djModel: DjModel) {

        if(!isUserSignedIn()){
            auth.createUserWithEmailAndPassword(djModel.login.email, djModel.login.password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        authStatusMessage.value = Event("User Account Created Successfully")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        authStatusMessage.value = Event("Authentication Failed")
                    }
                }
        }
        // [END create_user_with_email]
    }

    private fun isUserSignedIn(): Boolean {
        val currentUser = auth.currentUser
        return currentUser != null
    }
}
