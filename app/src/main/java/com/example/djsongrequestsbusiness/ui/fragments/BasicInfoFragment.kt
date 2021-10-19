package com.example.djsongrequestsbusiness.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.djsongrequestsbusiness.databinding.FragmentBasicInfoBinding
import com.example.djsongrequestsbusiness.ui.viewModels.BasicInfoViewModel
import java.lang.Exception

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
//        val viewModel: BasicInfoViewModel = ViewModelProvider(this)[BasicInfoViewModel::class.java]

        binding.buttonContinue.setOnClickListener(View.OnClickListener {
            val action = BasicInfoFragmentDirections.nextDestination(binding.edittextUsername.toString(), binding.edittextEmail.toString(), binding.edittextPassword.toString())
            try {
                Navigation.findNavController(view).navigate(action)
            }catch (e: Exception){
                println(e.stackTrace)
            }

        })

        return view
    }
}