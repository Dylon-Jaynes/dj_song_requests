package com.example.djsongrequestsbusiness.data.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class UserRepository(private val auth: FirebaseAuth) {

    fun userSignUp(email: String,password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }
}