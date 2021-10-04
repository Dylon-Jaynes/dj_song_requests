package com.example.djsongrequestsbusiness.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.djsongrequestsbusiness.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_DJSongRequestsBusiness)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}

