package com.example.wingstofly.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController

import com.example.wingstofly.R;
import com.example.wingstofly.databinding.FragmentNonHighBinding

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NonHighFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
class NonHighFragment: Fragment() {

    private lateinit var bind:FragmentNonHighBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentNonHighBinding.inflate(layoutInflater)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        bind.navView.bringToFront()
        bind.navView.setupWithNavController(navHostFragment.navController)

        return bind.root
    }

}