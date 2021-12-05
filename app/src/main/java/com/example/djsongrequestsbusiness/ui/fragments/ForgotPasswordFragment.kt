package com.example.djsongrequestsbusiness.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.djsongrequestsbusiness.databinding.FragmentForgotPasswordBinding
import com.example.djsongrequestsbusiness.ui.viewModels.ForgotPasswordViewModel

class ForgotPasswordFragment : Fragment() {

    private lateinit var viewModel: ForgotPasswordViewModel

    private var _binding: FragmentForgotPasswordBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ForgotPasswordViewModel::class.java]

        viewModel.setPasswordResetNotify.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.setEmailError.observe(viewLifecycleOwner, Observer {
            binding.edittextEmail.error = it.toString()
            binding.edittextEmail.requestFocus()
        })

        binding.buttonResetPass.setOnClickListener(View.OnClickListener {
            viewModel.onClickReset(binding.edittextEmail.text.toString())
        })
    }
}