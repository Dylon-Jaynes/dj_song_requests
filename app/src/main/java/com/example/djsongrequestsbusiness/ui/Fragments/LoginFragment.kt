package com.example.djsongrequestsbusiness.ui.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.djsongrequestsbusiness.R
import com.example.djsongrequestsbusiness.databinding.FragmentLoginBinding
import com.example.djsongrequestsbusiness.databinding.FragmentSongListBinding
import com.example.djsongrequestsbusiness.ui.ViewModels.LoginViewModel
import com.example.djsongrequestsbusiness.ui.ViewModels.SongListViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.textviewRegister.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(view).navigate(R.id.next_destination)
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}