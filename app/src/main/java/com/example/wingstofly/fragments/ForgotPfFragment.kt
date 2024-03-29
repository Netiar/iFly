package com.example.wingstofly.fragments;

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentForgotPfBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants
import com.example.wingstofly.utils.PfNotificationService
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForgotPfFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
class ForgotPfFragment: Fragment(){
    private lateinit var mAuth: DatabaseReference
    private lateinit var bind: FragmentForgotPfBinding
    private lateinit var prefEditor: SharedPreferences.Editor
    private lateinit var pref: SharedPreferences
    private lateinit var scholars: ArrayList<Scholar>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentForgotPfBinding.inflate(layoutInflater)
        startAnimations()

        mAuth = FirebaseDatabase.getInstance().reference
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        prefEditor = pref.edit()
        scholars = (activity as MainActivity).scholars


        val schools = HashSet<String>()
        val branches = HashSet<String>()
        for (scholar in scholars){
            schools.add(scholar.secondarySchool!!)
            branches.add(scholar.origin!!)
        }

        val adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, schools.toList())
        val adapterBranches = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, branches.toList())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterBranches.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bind.scholarId.adapter = adapter
        bind.confirmPassword.adapter = adapterBranches

        val service = PfNotificationService(requireContext())


        bind.submit.setOnClickListener{
            val scholarName = bind.firstName.editText!!.text.trim().toString()
            val scholarHighSchool = bind.scholarId.selectedItem.toString()
            val scholarBranch = bind.confirmPassword.selectedItem.toString()
            val scholarPhone = bind.scholarPhone.editText!!.text.trim().toString()


            var scholar: Scholar? = null
            for (i in 0 until scholars.size){
                if (scholars[i].origin == scholarBranch &&
                    scholars[i].name!! == scholarName &&
                    scholars[i].secondarySchool!! == scholarHighSchool){
                    scholar = scholars[i]

                    scholar.primaryNumber = scholarPhone
                    service.showNotification(scholar)
                    mAuth.child("user").child(scholar.name!!).setValue(scholar)

                    prefEditor.putString(Constants.PF_NUMBER, scholar.pfNumber).apply()

                    Toast.makeText(requireContext(), "Click on the notification to register.", Toast.LENGTH_LONG).show()
                    bind.firstName.isErrorEnabled = false
                    break
                }else{
                    bind.firstName.error = "Check your details"
                }
            }


        }


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
        bind.slogan.startAnimation(
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
        bind.scholarPhone.startAnimation(AnimationUtils.loadAnimation(
            requireContext(), R.anim.zoom_in
        ))

        bind.confirmPassword.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            ))
    }

}