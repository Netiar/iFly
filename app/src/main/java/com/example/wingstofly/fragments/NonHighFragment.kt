package com.example.wingstofly.fragments;

import android.os.Bundle;
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.wingstofly.R;
import com.example.wingstofly.databinding.FragmentNonHighBinding
import com.example.wingstofly.models.Scholar

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NonHighFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
class NonHighFragment: Fragment() , View.OnClickListener {
    private lateinit var scholar: Scholar
    private  val args: NonHighFragmentArgs by navArgs()

    private lateinit var bind:FragmentNonHighBinding

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
        window.statusBarColor = ContextCompat.getColor(requireActivity(),R.color.maroon3);


        //getting the animation transition from the login fragment
        val animationTransition = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )

        //initiating the animation transition
        sharedElementEnterTransition = animationTransition
        sharedElementReturnTransition = animationTransition
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentNonHighBinding.inflate(layoutInflater)

        startAnimations()

        //getting the scholar from the graph
        scholar = args.scholar

        //getting the navigation view header
        val header = bind.navView.getHeaderView(0)
        val scholarNameView = header.findViewById<TextView>(R.id.scholar_name)
        val scholarPfView = header.findViewById<TextView>(R.id.pfNumber)

        //binding the header views with data
        val scholarNames= scholar.name!!.split(" ")
        scholarNameView.text = scholarNames[0]
        scholarPfView.text = scholar.pfNumber
        bind.trvWelcome.text = "Welcome ${scholarNames[0]},"

        bind.image.setOnClickListener(this::onClick)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        bind.navView.bringToFront()
        bind.navView.setupWithNavController(navHostFragment.navController)

        return bind.root
    }

    private fun startAnimations() {
        bind.image.startAnimation(AnimationUtils.loadAnimation(
            requireContext(), R.anim.zoom_in
        ))
        bind.trvWelcome.startAnimation(AnimationUtils.loadAnimation(
            requireContext(), R.anim.zoom_in
        ))
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.image){
            bind.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

}