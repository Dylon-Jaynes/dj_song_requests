package com.example.djsongrequestsbusiness.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.djsongrequestsbusiness.Event
import com.example.djsongrequestsbusiness.data.dataClasses.DjModel
import com.example.djsongrequestsbusiness.data.repositories.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class DjIdViewModel(application: Application) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val authStatusMessage = MutableLiveData<Event<String>>()
    private val context by lazy { getApplication<Application>().applicationContext }

//    fun createAccount(djModel: DjModel) {
//        auth.createUserWithEmailAndPassword(djModel.login.email, djModel.login.password)
//            .addOnCompleteListener(context) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
////                    updateUI(user)
//                    Toast.makeText(context, "Authentication for user $user was successful!", Toast.LENGTH_SHORT).show()
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(context, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
////                    updateUI(null)
//                }
//            }
//    }

    fun onClickSignUp(djModel: DjModel): Task<AuthResult> {
        val userRepository = UserRepository(auth)
        return userRepository.userSignUp(djModel.login.email, djModel.login.password)
    }

//    private fun isUserSignedIn(): Boolean {
//        val currentUser = auth.currentUser
//        return currentUser != null
//    }

    private fun updateUI(user: FirebaseUser){

    }
}
