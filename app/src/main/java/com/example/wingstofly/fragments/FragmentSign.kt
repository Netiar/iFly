package com.example.wingstofly.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentSignBinding
import com.example.wingstofly.utils.Constants
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FragmentSign: Fragment(), View.OnClickListener {
    private lateinit var bind: FragmentSignBinding
    private lateinit var mAuth: DatabaseReference
    private lateinit var prefEditor: SharedPreferences.Editor
    private lateinit var pref: SharedPreferences


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentSignBinding.inflate(layoutInflater)

        startAnimations()
        mAuth = FirebaseDatabase.getInstance().reference
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        prefEditor = pref.edit()

        bind.forgot.setOnClickListener(this::onClick)
        bind.submit.setOnClickListener(this:: onClick)

        return bind.root
    }

    private fun startAnimations() {
        bind.image.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            ))
        bind.form.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            ))

        bind.confirmPassword.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            ))
        bind.scholarId.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            ))
        bind.firstName.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            ))
        bind.submit.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            ))
        bind.forgot.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            ))
    }


    override fun onClick(p0: View?) {
        if (p0 == bind.forgot){
            p0.findFragment<FragmentSign>().findNavController().navigate(R.id.action_fragmentSign2_to_forgotPfFragment)
        }

        if(p0 == bind.submit){
            val scholarNumber = bind.firstName.editText!!.text.trim().toString()
            val scholarPassword = bind.scholarId.editText!!.text.trim().toString()
            val scholarConfirm = bind.confirmPassword.editText!!.text.trim().toString()

            if(scholarPassword != scholarConfirm){
                bind.scholarId.isErrorEnabled = true
                bind.scholarId.error = "Passwords must match"
            }else{
                val scholars = (activity as MainActivity).scholars

                for (i in 0 until scholars.size){
                    if (scholarNumber == scholars[i].pfNumber){
                        val scholar = scholars[i]
                        scholar.password = scholarPassword
                        prefEditor.putString(Constants.PF_NUMBER, scholar.pfNumber).apply()
                        mAuth.child("scholars").child(scholar.name!!).setValue(scholar)

                        val testNumber = scholar.pfNumber!!.subSequence(0,2)
                        val bundle = Bundle().apply {
                            putSerializable("scholar", scholar)
                        }

                        //creating fragment navigator extras instance
                        val transitionExtras = FragmentNavigatorExtras(bind.image to "fragment_image")

                        if (testNumber == "pf"){
                            p0.findFragment<FragmentSign>().findNavController().navigate(R.id.action_fragmentSign2_to_highSchoolFragment, bundle, null, transitionExtras)

                        }else{
                            p0.findFragment<FragmentSign>().findNavController().navigate(R.id.action_fragmentSign2_to_nonHighFragment, bundle, null, transitionExtras)
                        }
                        bind.firstName.isErrorEnabled = false

                        break
                    }else{
                        bind.firstName.isErrorEnabled = true
                        bind.firstName.error = "Scholar name not found"
                    }
                }


            }
        }
    }

}