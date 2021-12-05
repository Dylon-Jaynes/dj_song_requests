package com.example.djsongrequestsbusiness.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.djsongrequestsbusiness.R
import com.example.djsongrequestsbusiness.data.dataClasses.LoginModel
import com.example.djsongrequestsbusiness.databinding.FragmentLoginBinding
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
            requireActivity().finish()
        }

        viewModel.displayUserLoginResult.observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.setEmailError.observe(viewLifecycleOwner, Observer { it ->
            binding.edittextEmail.error = it.toString()
            binding.edittextEmail.requestFocus()
        })

        viewModel.setPasswordError.observe(viewLifecycleOwner, Observer { it ->
            binding.edittextPassword.error = it.toString()
            binding.edittextPassword.requestFocus()
        })

        // Navigates to the next fragment if the event has never been handled before.
        viewModel.navigateToSongListFrag.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Navigation.findNavController(view).navigate(R.id.to_main_frag)
            }
        })

        handleOnClick(view)
    }

    private fun handleOnClick(view: View){
        binding.buttonRegister.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(view).navigate(R.id.next_destination)
        })

        binding.buttonLogin.setOnClickListener(View.OnClickListener {
            val loginCredentials = LoginModel(binding.edittextEmail.text.toString().trim(), binding.edittextPassword.text.toString().trim(), "")
            viewModel.onClickLogin(loginCredentials)
        })

        binding.textviewForgotPassword.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(view).navigate(R.id.to_forgot_password_frag)
        })

    }

    override fun onStart() {
        super.onStart()
        //hide action bar from splash screen
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}