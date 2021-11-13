package com.example.djsongrequestsbusiness.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.djsongrequestsbusiness.Event
import com.example.djsongrequestsbusiness.data.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth

class SongListViewModel(application: Application) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val userRepository = UserRepository(auth)

    private val _navigateToLoginFrag = MutableLiveData<Event<Boolean>>()
    val navigateToLoginFrag: LiveData<Event<Boolean>>
        get() = _navigateToLoginFrag

    fun isUserSignedIn() {
        if (userRepository.getUser() != null) {
            return
        }else{
            _navigateToLoginFrag.value = Event(true)
        }
    }

    fun signOutUser() {
        auth.signOut()
        _navigateToLoginFrag.value = Event(true)
    }
}