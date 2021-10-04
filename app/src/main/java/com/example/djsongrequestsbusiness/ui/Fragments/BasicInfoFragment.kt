package com.example.djsongrequestsbusiness.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.djsongrequestsbusiness.R

class BasicInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_basic_info, container, false)

        view.findViewById<Button>(R.id.button_continue).setOnClickListener { Navigation.findNavController(view).navigate(R.id.next_destination) }

        return view
    }
}