package com.example.djsongrequestsbusiness.Data.Repositories

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import java.security.Key

class SharedPrefsRepo(val context: Context) {

    private val MY_PREFS = "my_prefs"
    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE)
    private val editor = sharedPrefs.edit()

    fun savePref(KEY_NAME: String, value: String) {
        editor.putString(KEY_NAME, value)
        editor.commit()
    }

    fun savePref(KEY_NAME: String, value: Int) {
        editor.putInt(KEY_NAME, value)
        editor.commit()
    }

    fun savePref(KEY_NAME: String, value: Boolean) {
        editor.putBoolean(KEY_NAME, value)
        editor.commit()
    }

    fun getValueString(KEY_NAME: String): String? {
        return sharedPrefs.getString(KEY_NAME, null)
    }

    fun getValueInt(KEY_NAME: String): Int {
        return sharedPrefs.getInt(KEY_NAME, 0)
    }

    fun getValueBoolean(KEY_NAME: String, defaultValue: Boolean): Boolean {
        return sharedPrefs.getBoolean(KEY_NAME, defaultValue)
    }

    fun clearSharedPreferences() {
        editor.clear()
        editor.commit()
    }

    fun removeValue(KEY_NAME: String) {
        editor.remove(KEY_NAME)
        editor.commit()
    }
}