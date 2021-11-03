package com.example.djsongrequestsbusiness.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.djsongrequestsbusiness.R
import com.example.djsongrequestsbusiness.data.dataClasses.LoginModel
import com.example.djsongrequestsbusiness.databinding.FragmentBasicInfoBinding
import com.example.djsongrequestsbusiness.ui.viewModels.BasicInfoViewModel
import androidx.lifecycle.Observer

class BasicInfoFragment : Fragment() {

    private lateinit var viewModel: BasicInfoViewModel

    private var _binding: FragmentBasicInfoBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBasicInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the viewModel
        viewModel = ViewModelProvider(this)[BasicInfoViewModel::class.java]

        // Display the result of the Task<AuthResult> returned from Firebase.
        viewModel.displayCreateUserResult.observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }

        })

        // Set field errors if required.
        viewModel.setEmailError.observe(viewLifecycleOwner, Observer { it ->
            binding.edittextEmail.error = it.toString()
            binding.edittextEmail.requestFocus()
        })
        viewModel.setPasswordError.observe(viewLifecycleOwner, Observer { it ->
            binding.edittextPassword.error = it.toString()
            binding.edittextPassword.requestFocus()
        })

        // Navigates to the next fragment if the event has never been handled before.
        viewModel.navigateToDjIdFrag.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Navigation.findNavController(view).navigate(R.id.next_destination)
            }
        })

        // Sends user back to login onBackPressed.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Navigation.findNavController(view).navigate(R.id.to_login_frag)
        }

        displayCreateUserResult(view)

    }

    private fun displayCreateUserResult(view: View) {
        binding.buttonContinue.setOnClickListener(View.OnClickListener {
            val newLogin = LoginModel(binding.edittextEmail.text.toString().trim(), binding.edittextPassword.text.toString().trim())
            viewModel.onClickSignUp(newLogin)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
