package com.example.djsongrequestsbusiness.ui.fragments

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.djsongrequestsbusiness.R
import com.example.djsongrequestsbusiness.data.dataClasses.LoginModel
import com.example.djsongrequestsbusiness.databinding.FragmentBasicInfoBinding
import com.example.djsongrequestsbusiness.ui.viewModels.BasicInfoViewModel
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import androidx.annotation.NonNull
import androidx.navigation.NavController


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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: BasicInfoViewModel = ViewModelProvider(this)[BasicInfoViewModel::class.java]

        // Sends user back to login onBackPressed.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Navigation.findNavController(view).navigate(R.id.to_login_frag)
        }

        binding.buttonContinue.setOnClickListener(View.OnClickListener {
            val newLogin = LoginModel(binding.edittextEmail.text.toString().trim(), binding.edittextPassword.text.toString().trim())
            val taskResult = viewModel.onClickSignUp(newLogin)
            taskResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    Toast.makeText(context, "Your account has been successfully created!", Toast.LENGTH_SHORT).show()
                    val navController = Navigation.findNavController(view)
                    navController.navigate(R.id.next_destination)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    binding.edittextEmail.error = "This email address is already in use by another account."
                    }
                }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}