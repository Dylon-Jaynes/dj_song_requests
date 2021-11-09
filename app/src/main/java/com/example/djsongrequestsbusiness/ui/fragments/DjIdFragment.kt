package com.example.djsongrequestsbusiness.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.djsongrequestsbusiness.R
import com.example.djsongrequestsbusiness.databinding.FragmentDjIdBinding
import com.example.djsongrequestsbusiness.ui.viewModels.DjIdViewModel

class DjIdFragment : Fragment() {

    private var _binding: FragmentDjIdBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get()= _binding!!

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

        // Sends user back to login onBackPressed.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Navigation.findNavController(view).navigate(R.id.to_login_frag)
        }

        binding.buttonSend.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(view).navigate(R.id.to_main_frag)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
