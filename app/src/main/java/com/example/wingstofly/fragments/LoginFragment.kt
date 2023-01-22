package com.example.wingstofly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentSignBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Validator
import kotlinx.android.synthetic.main.fragment_sign.*


class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var bind:FragmentSignBinding
    private lateinit var scholars: ArrayList<Scholar>
    private lateinit var scholar: Scholar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bind = FragmentSignBinding.inflate(layoutInflater)

        scholars = (activity as MainActivity).scholars

        bind.submit.setOnClickListener(this::onClick)



        return bind.root
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.submit){
            validatePfNumber(p0)
        }
    }

    private fun validatePfNumber(p0: View?){

            if (!Validator().validateInputText(scholar_id)){
                return
            }
        val pfNumber = bind.scholarId.editText!!.text.trim()

        for (i in 0 until scholars.size){
            val currentPfNumber = scholars[i].pfNumber
            if (pfNumber.contentEquals(currentPfNumber, true)){
                scholar = scholars[i]
                p0?.findFragment<LoginFragment>()?.findNavController()?.navigate(R.id.action_loginFragment_to_mainFragment)
                break
            }else{
                scholar_id.error = "Enter the correct number"
            }
        }
    }
}