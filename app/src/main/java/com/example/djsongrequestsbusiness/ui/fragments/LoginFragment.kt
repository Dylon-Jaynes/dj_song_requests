package com.example.djsongrequestsbusiness.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.djsongrequestsbusiness.R
import com.example.djsongrequestsbusiness.databinding.FragmentLoginBinding
import com.example.djsongrequestsbusiness.ui.viewModels.DjIdViewModel
import com.example.djsongrequestsbusiness.ui.viewModels.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private var _binding: FragmentLoginBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        // Sends user back to SongListFragment onBackPressed.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Navigation.findNavController(view).navigate(R.id.to_main_frag)
        }

        handleOnClick(view)
    }

    private fun handleOnClick(view: View){
        binding.buttonRegister.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(view).navigate(R.id.next_destination)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}