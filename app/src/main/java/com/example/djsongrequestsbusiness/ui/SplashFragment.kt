package com.example.djsongrequestsbusiness.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.djsongrequestsbusiness.R
import kotlinx.coroutines.delay

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        view.findViewById<ImageView>(R.id.splash_background).setOnClickListener { Navigation.findNavController(view).navigate(R.id.next_destination) }

        return view
    }



}

