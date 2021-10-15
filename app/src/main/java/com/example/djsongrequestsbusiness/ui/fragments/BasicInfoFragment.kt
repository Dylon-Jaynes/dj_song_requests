package com.example.djsongrequestsbusiness.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.djsongrequestsbusiness.R
import com.example.djsongrequestsbusiness.data.dataClasses.LoginModel
import com.example.djsongrequestsbusiness.databinding.FragmentBasicInfoBinding
import com.example.djsongrequestsbusiness.ui.viewModels.BasicInfoViewModel

class BasicInfoFragment : Fragment() {

    private var _binding: FragmentBasicInfoBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBasicInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModel: BasicInfoViewModel = ViewModelProvider(this)[BasicInfoViewModel::class.java]

        binding.buttonContinue.setOnClickListener(View.OnClickListener {
            val newLogin = LoginModel(binding.edittextUsername.text.toString(), binding.edittextEmail.text.toString(), binding.edittextPassword.text.toString())
            val bundle = Bundle()
            bundle.putParcelable("loginModel", newLogin)
            Navigation.findNavController(view).navigate(R.id.next_destination, bundle)
        })

        return view
    }
}