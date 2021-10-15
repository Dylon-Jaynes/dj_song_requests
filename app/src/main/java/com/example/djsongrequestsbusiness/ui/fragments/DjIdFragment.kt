package com.example.djsongrequestsbusiness.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.djsongrequestsbusiness.data.dataClasses.DjModel
import com.example.djsongrequestsbusiness.databinding.FragmentDjIdBinding
import com.example.djsongrequestsbusiness.ui.viewModels.DjIdViewModel
import com.google.firebase.database.DatabaseReference

class DjIdFragment : Fragment() {

    private var _binding: FragmentDjIdBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get()= _binding!!
//    private lateinit var database: DatabaseReference
    private val viewModel: DjIdViewModel = ViewModelProvider(this)[DjIdViewModel::class.java]


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDjIdBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    val args: DjIdFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonSend.setOnClickListener(View.OnClickListener {
            val newDj = DjModel(binding.edittextDjId.text.toString(), args.loginModel!!)
            viewModel.createAccount(newDj)
        })
    }
}