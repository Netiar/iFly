package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentLoginBinding


class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var bind:FragmentLoginBinding

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
        bind = FragmentLoginBinding.inflate(layoutInflater)
        bind.submit.setOnClickListener(this::onClick)

        return bind.root
    }

    companion object {
      @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.submit){
            p0.findFragment<LoginFragment>().findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
//            val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//            navHostFragment.navController.navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }
}