package com.example.wingstofly.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentHighSchoolBinding
import com.example.wingstofly.viewmodel.QuizViewModel


class HighSchoolFragment : Fragment(), View.OnClickListener {

    private lateinit var bind:FragmentHighSchoolBinding
    private lateinit var viewModel: QuizViewModel
    val args: HighSchoolFragmentArgs by navArgs()

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


        //getting  the animation transition
        val animationTransition = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )

        sharedElementEnterTransition = animationTransition
        sharedElementReturnTransition = animationTransition
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentHighSchoolBinding.inflate(layoutInflater)
        val scholar = args.scholar

        startAnimations()

        viewModel = (activity as MainActivity).questViewModel

        bind.image.setOnClickListener(this::onClick)
        val scholarNames = scholar.name!!.split(" ")
        bind.trvWelcome.text = "Welcome ${scholarNames[0]}"

        // getting the navigation header components
        val header = bind.navView.getHeaderView(0)
        val scholarName = header.findViewById<TextView>(R.id.scholar_name)
        val scholarNumber = header.findViewById<TextView>(R.id.pfNumber)

        // Binding the header elements data.
        scholarName.text = scholar.name
        scholarNumber.text = scholar.pfNumber


        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        bind.navView.bringToFront()
        bind.navView.setupWithNavController(navHostFragment.navController)
        return bind.root
    }

    private fun startAnimations() {
        bind.image.startAnimation(
            AnimationUtils.loadAnimation(
            requireContext(), R.anim.zoom_in
        ))
        bind.trvWelcome.startAnimation(
            AnimationUtils.loadAnimation(
            requireContext(), R.anim.zoom_in
        ))
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.image){
            bind.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

}