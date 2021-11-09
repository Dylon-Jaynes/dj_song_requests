package com.example.djsongrequestsbusiness.data.repositories


import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*

class UserRepository(private val auth: FirebaseAuth) {

    fun userSignUp(email: String,password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun userLogin(email: String,password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }
}