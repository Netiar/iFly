package com.example.wingstofly.fragments;

import android.content.SharedPreferences
import android.os.Bundle;
import android.preference.PreferenceManager

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.wingstofly.MainActivity

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
class ForgotPfFragment: Fragment(), View.OnClickListener {
    private lateinit var mAuth: DatabaseReference
    private lateinit var bind: FragmentForgotPfBinding
    private lateinit var prefEditor: SharedPreferences.Editor
    private lateinit var pref: SharedPreferences

    lateinit var scholar: Scholar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentForgotPfBinding.inflate(layoutInflater)

        mAuth = FirebaseDatabase.getInstance().reference
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        prefEditor = pref.edit()

        bind.submit.setOnClickListener(this::onClick)


        return bind.root
    }

    private fun saveToFirebase(scholar: Scholar) {
        mAuth.child("user").child(scholar.name!!).setValue(scholar)
    }

    private fun getScholarData(scholars: ArrayList<Scholar>): Scholar {
        var scholar: Scholar? = null

        val scholarName = bind.firstName.editText!!.text.trim().toString()
        val scholarHighSchool = bind.confirmPassword.editText!!.text.trim().toString()
        val scholarBranch = bind.scholarId.editText!!.text.trim().toString()
        val scholarPhone = bind.scholarPhone.editText!!.text.trim().toString()

        for (i in 0 until scholars.size){
            if (scholars[i].origin.equals(scholarBranch,true) &&
            scholars[i].name!!.equals(scholarName, true) &&
                    scholars[i].secondarySchool!!.equals(scholarHighSchool, true)){
                scholar = scholars[i]
                scholar.primaryNumber = scholarPhone
                prefEditor.putString(Constants.PF_NUMBER, scholar.pfNumber).apply()

            }else{
                if (scholars[i].origin!! != scholarBranch){
                    bind.scholarId.isErrorEnabled = true
                    bind.scholarId.error = "Did you mean ${scholars[i].origin}"
                }else if (scholars[i].name != scholarName){
                    bind.firstName.isErrorEnabled = true
                    bind.firstName.error = "Did you mean ${scholars[i].name}"

                }else{
                    bind.confirmPassword.isErrorEnabled = true
                    bind.confirmPassword.error = "Did you mean ${scholars[i].secondarySchool}"
                }
            }
        }

        return scholar!!
    }

    override fun onClick(p0: View?) {

        val scholars = (activity as MainActivity).scholars
        scholar = getScholarData(scholars)
        saveToFirebase(scholar)

        val service = PfNotificationService(requireContext())
        service.showNotification(scholar)
    }

}