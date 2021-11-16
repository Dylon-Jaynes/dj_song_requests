package com.example.djsongrequestsbusiness.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.djsongrequestsbusiness.R
import com.example.djsongrequestsbusiness.data.dataClasses.LoginModel
import com.example.djsongrequestsbusiness.databinding.FragmentDjIdBinding
import com.example.djsongrequestsbusiness.ui.viewModels.DjIdViewModel

class DjIdFragment : Fragment() {

    private var _binding: FragmentDjIdBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get()= _binding!!
    val args: DjIdFragmentArgs by navArgs()

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

        // Display an error toast if the user can't be added to the database.
        viewModel.displayAddUserResult.observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })

        // Listen for navigation event which takes a value if the user is
        // successfully added to the database.
        viewModel.navigateToSongListFragment.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Navigation.findNavController(view).navigate(R.id.to_main_frag)
            }
        })

        // Sends user back to login onBackPressed.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Navigation.findNavController(view).navigate(R.id.to_login_frag)
        }

        // Attempt to add user to the database.
        binding.buttonSend.setOnClickListener(View.OnClickListener {
            val loginModel = LoginModel(args.email, args.password, args.username.toString())
            viewModel.addUser(binding.edittextDjId.text.toString(), loginModel)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
