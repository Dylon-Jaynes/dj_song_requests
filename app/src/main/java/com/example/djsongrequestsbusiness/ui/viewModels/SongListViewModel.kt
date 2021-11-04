package com.example.djsongrequestsbusiness.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SongListViewModel(val app: Application)
    : AndroidViewModel(app) {

    private val sharedPrefsRepo = SharedPrefsRepo(app.applicationContext)

    // Declares a SingleLiveEvent which will only deliver the event to an observer
    // that is Observing at the time of the event.
//    val isFirstRunEvent = Event<Boolean>()
//
//    fun isFirstRun(KEY_NAME: String, defaultValue: Boolean) {
//        if(sharedPrefsRepo.getValueBoolean(KEY_NAME, defaultValue)){
//            isFirstRunEvent.value = sharedPrefsRepo.getValueBoolean(KEY_NAME, defaultValue)
//            sharedPrefsRepo.savePref(KEY_NAME, false)
//        }
//    }

    fun saveSharedPref(KEY_NAME: String, value: Boolean) {
        sharedPrefsRepo.savePref(KEY_NAME, value)
    }


}