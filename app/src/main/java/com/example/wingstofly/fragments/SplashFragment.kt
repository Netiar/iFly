package com.example.wingstofly.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentSplashBinding

class SplashFragment: Fragment() {
    private lateinit var bind: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hiding the status bar
        val window = requireActivity().window
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        // Configure the behavior of the hidden system bars.
        windowInsetsController.isAppearanceLightStatusBars = true


        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.statusBarColor = ContextCompat.getColor(requireActivity(),R.color.maroon2);

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentSplashBinding.inflate(layoutInflater)

        //adding a zoom in effect to the image
        bind.image.startAnimation(AnimationUtils.loadAnimation(
            requireContext(), R.anim.zoom_in
        ))
        bind.form.startAnimation(AnimationUtils.loadAnimation(
            requireContext(), R.anim.zoom_in
        ))
        bind.slogan.startAnimation(AnimationUtils.loadAnimation(
            requireContext(), R.anim.zoom_in
        ))

        //creating navigation extras
        val extras = FragmentNavigatorExtras(bind.image to "login_image")

        //creating a splash screen
        Handler(Looper.getMainLooper()).postDelayed(
            {
                NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_loginFragment, null, null, extras)
            }, 2000
        )
        return bind.root
    }

    override fun onResume() {
        super.onResume()
        //hiding the status bar
        val window = requireActivity().window
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        // Configure the behavior of the hidden system bars.
        windowInsetsController.isAppearanceLightStatusBars = true


        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.statusBarColor = ContextCompat.getColor(requireActivity(),R.color.maroon2);

    }

}