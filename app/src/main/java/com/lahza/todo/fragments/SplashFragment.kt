package com.lahza.todo.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.lahza.todo.R

class SplashFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        checkUserLoginStatus()
    }

    private fun checkUserLoginStatus() {
        val isUserLoggedIn: Boolean = auth.currentUser != null
        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            if (isUserLoggedIn) {
                navigateToHomeFragment()
            } else {
                navigateToSignInFragment()
            }
        }, 2000)
    }

    private fun navigateToSignInFragment() {
        navController.navigate(R.id.action_splashFragment_to_signInFragment)
    }

    private fun navigateToHomeFragment() {
        navController.navigate(R.id.action_splashFragment_to_homeFragment)
    }

    private fun init(view: View) {
        auth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
    }

}