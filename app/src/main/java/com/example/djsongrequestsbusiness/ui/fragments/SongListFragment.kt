package com.example.djsongrequestsbusiness.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.djsongrequestsbusiness.R
import com.example.djsongrequestsbusiness.databinding.FragmentSongListBinding
import com.example.djsongrequestsbusiness.ui.viewModels.SongListViewModel

class SongListFragment() : Fragment() {

    private lateinit var viewModel: SongListViewModel

    private var _binding: FragmentSongListBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = FragmentSongListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the viewModel
        viewModel = ViewModelProvider(this)[SongListViewModel::class.java]

        viewModel.navigateToLoginFrag.observe(viewLifecycleOwner, Observer { it ->
            it.getContentIfNotHandled()?.let {
                Navigation.findNavController(view).navigate(R.id.next_destination)
            }
        })

        viewModel.isUserSignedIn()

        // Ends MainActivity onBackPressed.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
        val logoutItem = menu[0]
        // Listens for logout click in the menu drop down list.
        logoutItem.setOnMenuItemClickListener {
            onOptionsItemSelected(logoutItem)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_logout -> {
                viewModel.signOutUser()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}