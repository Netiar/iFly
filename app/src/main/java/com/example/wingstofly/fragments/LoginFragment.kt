package com.example.wingstofly.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentLoginBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants
import com.example.wingstofly.utils.Validator
import kotlinx.android.synthetic.main.fragment_sign.*


class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var bind:FragmentLoginBinding
    private lateinit var scholars: ArrayList<Scholar>
    private lateinit var pref: SharedPreferences
    lateinit var scholar: Scholar
    private  var pfNumber: String? = null
    private lateinit var prefEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val decorView = requireActivity().window.decorView // Hide the status bar.
//
//        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
//        decorView.systemUiVisibility = uiOptions
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        bind = FragmentLoginBinding.inflate(layoutInflater)

        scholars = (activity as MainActivity).scholars
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        prefEditor = pref.edit()
        bind.submit.setOnClickListener(this::onClick)
        bind.forgot1.setOnClickListener(this::onClick)



        return bind.root
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.submit){
            if (!Validator().validateInputText(scholar_id)){
                return
            }
            pfNumber = bind.firstName.editText!!.text.trim().toString()

            for (databaseScholar in scholars){
                val currentPfNumber = databaseScholar.pfNumber
                if (currentPfNumber.contentEquals(pfNumber, true)){
                    scholar = databaseScholar
                    prefEditor.putString(Constants.PF_NUMBER, currentPfNumber).apply()
                    val testNumber = pfNumber!!.subSequence(0,2)
                    val bundle = Bundle().apply {
                        putSerializable("scholar", scholar)
                    }
                    if (testNumber == "pf"){
                        p0.findFragment<LoginFragment>().findNavController().navigate(R.id.action_loginFragment_to_highSchoolFragment, bundle)
                    }else{
                        p0.findFragment<LoginFragment>().findNavController().navigate(R.id.action_loginFragment_to_nonHighFragment, bundle)
                    }
                    break
                }else{
                    bind.scholarId.error = "Pf Number not found"

                }
            }

        }
    }


    private fun getCurrentScholar(pfNumber: String): Scholar {
        var scholar:Scholar? = null
        for (i in 0 until scholars.size){
            val currentPfNumber = scholars[i].pfNumber
            if (currentPfNumber.contentEquals(pfNumber, true)){
                scholar = scholars[i]
                prefEditor.putString(Constants.PF_NUMBER, pfNumber).apply()
            }else{
                scholar_id.error = "Enter the correct number"

            }
        }
        return scholar!!
    }
}