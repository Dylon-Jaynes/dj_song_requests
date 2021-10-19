package com.example.djsongrequestsbusiness.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.djsongrequestsbusiness.data.dataClasses.DjModel
import com.example.djsongrequestsbusiness.data.dataClasses.LoginModel
import com.example.djsongrequestsbusiness.databinding.FragmentDjIdBinding
import com.example.djsongrequestsbusiness.ui.viewModels.DjIdViewModel

class DjIdFragment : Fragment() {

    private var _binding: FragmentDjIdBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get()= _binding!!
//    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDjIdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: DjIdViewModel = ViewModelProvider(this)[DjIdViewModel::class.java]

        binding.buttonSend.setOnClickListener(View.OnClickListener {
            val args: DjIdFragmentArgs by navArgs<DjIdFragmentArgs>()
            val newLogin = LoginModel(args.email, args.password)
            val newDj = DjModel(args.username, binding.edittextDjId.toString(), newLogin)
            viewModel.createAccount(newDj)
        })
    }
}