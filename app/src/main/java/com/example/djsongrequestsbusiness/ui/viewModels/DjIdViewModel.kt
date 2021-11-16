package com.example.djsongrequestsbusiness.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.djsongrequestsbusiness.Event
import com.example.djsongrequestsbusiness.data.dataClasses.LoginModel
import com.example.djsongrequestsbusiness.data.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth

class DjIdViewModel() : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _displayAddUserResult = MutableLiveData<Event<String>>()
    val displayAddUserResult: LiveData<Event<String>>
        get() = _displayAddUserResult

    private val _navigateToSongListFragment = MutableLiveData<Event<Boolean>>()
    val navigateToSongListFragment: LiveData<Event<Boolean>>
        get() = _navigateToSongListFragment


    fun addUser(djId: String, loginModel: LoginModel) {
        val userRepository = UserRepository(auth)

        val addUserResult = userRepository.addUser(djId, loginModel)
        if (!addUserResult) {
            _displayAddUserResult.value = Event("There was an error adding the user to the database.")
        }else {
            _navigateToSongListFragment.value = Event(true)
        }
    }

}
