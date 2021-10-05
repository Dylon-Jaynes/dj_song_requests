package com.example.djsongrequestsbusiness.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.djsongrequestsbusiness.R
import com.example.djsongrequestsbusiness.databinding.FragmentSongListBinding
import com.example.djsongrequestsbusiness.ui.ViewModels.SongListViewModel

class SongListFragment : Fragment() {

    private var _binding: FragmentSongListBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSongListBinding.inflate(inflater, container, false)
        val view = binding.root
        val songListViewModel = SongListViewModel(requireActivity().application)

        songListViewModel.isFirstRun("isFirstRun", true)

        songListViewModel.isFirstRunEvent.observe(viewLifecycleOwner) {
            Navigation.findNavController(view).navigate(R.id.next_destination,null, NavOptions.Builder().setPopUpTo(findNavController().graph.startDestination, true).build())
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}