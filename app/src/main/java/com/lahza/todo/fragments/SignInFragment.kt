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
import com.lahza.todo.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.tvSignIn.setOnClickListener {
            navigateToSignUpFragment()
        }

        binding.nextButton.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (isValidInput(email, password)) {
                loginUser(email, password)
            } else {
                showToast("Empty fields are not allowed")
            }
        }
    }

    private fun isValidInput(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun loginUser(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful)
                navigateToHomeFragment()
            else
                showToast(it.exception?.message ?: "Login failed")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun init(view: View) {
        auth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
    }

    private fun navigateToSignUpFragment() {
        navController.navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    private fun navigateToHomeFragment() {
        navController.navigate(R.id.action_signInFragment_to_homeFragment)
    }
}