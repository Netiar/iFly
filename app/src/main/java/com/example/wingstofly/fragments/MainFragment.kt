package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentMainBinding


class MainFragment : Fragment(), View.OnClickListener {
    private lateinit var bind:FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentMainBinding.inflate(layoutInflater)
        bind.image.setOnClickListener(this::onClick)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        bind.navView.bringToFront()
        bind.navView.setupWithNavController(navHostFragment.navController)
        return bind.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.image){
            bind.drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}