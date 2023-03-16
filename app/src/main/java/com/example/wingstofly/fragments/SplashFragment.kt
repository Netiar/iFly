package com.example.wingstofly.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentSplashBinding

class SplashFragment: Fragment() {
    private lateinit var bind: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentSplashBinding.inflate(layoutInflater)

        //creating navigation extras
        val extras = FragmentNavigatorExtras(bind.image to "login_image")

        //creating a splash screen
        Handler(Looper.getMainLooper()).postDelayed(
            {
                NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_loginFragment, null, null, extras)
            }, 3000
        )
        return bind.root
    }
}