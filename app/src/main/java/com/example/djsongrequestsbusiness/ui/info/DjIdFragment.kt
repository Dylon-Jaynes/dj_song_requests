package com.example.djsongrequestsbusiness.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.djsongrequestsbusiness.R

class DjIdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dj_id, container, false)

        val viewModel: DjIdViewModel = ViewModelProvider(this)[DjIdViewModel::class.java]

        view.findViewById<Button>(R.id.button_send).setOnClickListener { Navigation.findNavController(view).navigate(R.id.next_destination) }

        return view
    }
}