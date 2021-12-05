package com.example.djsongrequestsbusiness.data.repositories

import com.example.djsongrequestsbusiness.data.dataClasses.DjModel
import com.example.djsongrequestsbusiness.data.dataClasses.LoginModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class UserRepository(private val auth: FirebaseAuth) {

    private lateinit var database: DatabaseReference

    fun userSignUp(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun userLogin(email: String,password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun getUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun addUser(djId: String, loginModel: LoginModel): Boolean {
        database = FirebaseDatabase.getInstance().getReference("Users")
        val djModel = DjModel(djId, loginModel)
        try {
            database.child(djId).setValue(djModel)
        } catch (e: Exception) {
            return false
        }

        return true

    }

    fun passwordReset(email: String): Task<Void> {
        return auth.sendPasswordResetEmail(email)
    }
}