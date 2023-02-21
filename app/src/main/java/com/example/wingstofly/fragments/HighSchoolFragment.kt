package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentHighSchoolBinding
import com.example.wingstofly.viewmodel.QuizViewModel


class HighSchoolFragment : Fragment(), View.OnClickListener {

    private lateinit var bind:FragmentHighSchoolBinding
    lateinit var viewModel: QuizViewModel
    val args: HighSchoolFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentHighSchoolBinding.inflate(layoutInflater)
        val scholar = args.scholar

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

    override fun onClick(p0: View?) {
        if (p0 == bind.image){
            bind.drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}