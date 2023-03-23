package com.example.wingstofly.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentLoginBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants
import com.example.wingstofly.utils.Validator
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_sign.*


class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var mAuth: DatabaseReference
    private lateinit var bind:FragmentLoginBinding
    private lateinit var scholars: ArrayList<Scholar>
    private lateinit var pref: SharedPreferences
    lateinit var scholar: Scholar
    private  var pfNumber: String? = null
    private lateinit var prefEditor: SharedPreferences.Editor

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
        window.statusBarColor = ContextCompat.getColor(requireActivity(),R.color.maroon2);

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bind = FragmentLoginBinding.inflate(layoutInflater)
        mAuth = FirebaseDatabase.getInstance().reference

        startAnimations()

        scholars = getScholars()
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        prefEditor = pref.edit()
        bind.submit.setOnClickListener(this::onClick)
        bind.forgot1.setOnClickListener(this::onClick)



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
        bind.forgot1.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            ))
        bind.forgot.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            ))
    }

    private fun getScholars(): ArrayList<Scholar> {
        val list = ArrayList<Scholar>()

        mAuth.child("user").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){
                    list.add(data.getValue(Scholar::class.java)!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
        return list
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.submit){
            if (!Validator().validateInputText(scholar_id)){
                return
            }
            pfNumber = bind.firstName.editText!!.text.trim().toString()
            val password = bind.scholarId.editText!!.text.trim().toString()

            for (databaseScholar in scholars){
                val currentPfNumber = databaseScholar.pfNumber
                if (currentPfNumber.contentEquals(pfNumber, true)){
                    if(password.contentEquals(databaseScholar.password)){
                        scholar = databaseScholar
                        prefEditor.putString(Constants.PF_NUMBER, currentPfNumber).apply()
                        val testNumber = pfNumber!!.subSequence(0,2)
                        val bundle = Bundle().apply {
                            putSerializable("scholar", scholar)
                        }
                        //creating fragment navigator extras instance
                        val transitionExtras = FragmentNavigatorExtras(bind.image to "fragment_image", bind.slogan to "main_slogan")

                        if (testNumber == "pf"){
                            p0.findFragment<LoginFragment>().findNavController().navigate(R.id.action_loginFragment_to_highSchoolFragment, bundle, null, transitionExtras)
                            bind.scholarId.isErrorEnabled = false

                        }else{
                            p0.findFragment<LoginFragment>().findNavController().navigate(R.id.action_loginFragment_to_nonHighFragment, bundle, null, transitionExtras)

                        }
                        bind.scholarId.isErrorEnabled = false
                        bind.firstName.isErrorEnabled = false

                        break
                    }else{
                        bind.scholarId.error = "Pf Number not found"

                    }

                }else{
                    bind.firstName.error = "Pf Number not found"

                }
            }

        }
        if(p0 == bind.forgot1){
            stopAnimations()
            val transitionExtras = FragmentNavigatorExtras(bind.image to "image_sign")
            p0.findFragment<LoginFragment>().findNavController().navigate(R.id.action_loginFragment_to_fragmentSign2, null, null, transitionExtras)
        }
    }

    private fun stopAnimations() {
        bind.image.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_out
            ))
        bind.form.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_out
            ))
        bind.slogan.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_out
            ))
        bind.scholarId.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_out
            ))
        bind.firstName.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_out
            ))
        bind.submit.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_out
            ))
        bind.forgot1.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_out
            ))
        bind.forgot.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_out
            ))
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

    override fun onResume() {
        super.onResume()
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
        window.statusBarColor = ContextCompat.getColor(requireActivity(),R.color.maroon2);

    }

}