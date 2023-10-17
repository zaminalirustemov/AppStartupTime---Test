package com.lahza.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.lahza.todo.R
import com.lahza.todo.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.tvSignIn.setOnClickListener {
            navigateToSignInFragment()
        }

        binding.nextButton.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val verifyPassword = binding.etRetypePassword.text.toString().trim()

            if (isValidInput(email, password, verifyPassword)) {
                if (password == verifyPassword) {
                    registerUser(email, password)
                } else {
                    showToast("Passwords do not match")
                }
            } else {
                showToast("Empty fields are not allowed")
            }
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful)
                navigateToHomeFragment()
            else
                showToast(it.exception?.message ?: "Registration failed")
        }
    }

    private fun init(view: View) {
        auth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
    }


    private fun isValidInput(email: String, password: String, verifyPassword: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty() && verifyPassword.isNotEmpty()
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToSignInFragment() {
        navController.navigate(R.id.action_signUpFragment_to_signInFragment)
    }

    private fun navigateToHomeFragment() {
        navController.navigate(R.id.action_signUpFragment_to_homeFragment)
    }
}