package com.example.wingstofly.fragments

import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.databinding.FragmentEditInfoBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.*


class EditInfoFragment : Fragment(){
    private lateinit var mAuth: DatabaseReference
    private lateinit var scholars: ArrayList<Scholar>
    private lateinit var bind: FragmentEditInfoBinding
    private lateinit var pref: SharedPreferences
    private lateinit var bottomSheet: BottomSheetDialog
    lateinit var scholar: Scholar


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
        bind = FragmentEditInfoBinding.inflate(layoutInflater)
        bottomSheet = BottomSheetDialog(requireContext())



        mAuth = FirebaseDatabase.getInstance().reference
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        scholars = (activity as MainActivity).scholars
        val pfNumber = pref.getString(Constants.PF_NUMBER, null)


        for (databaseScholar in scholars){
            val currentPfNumber = databaseScholar.pfNumber
            if (currentPfNumber.contentEquals(pfNumber, true)){
                scholar = databaseScholar

                bind.highSchool.text = "High-School: ${scholar.secondarySchool}"
                bind.highGrade.text = "Mean-Grade: ${scholar.meanGrade}"
                bind.highMarks.text = "Mean-AGP: ${scholar.meanAGP.toString()}"
                bind.primarySchool.text = "Primary School: ${scholar.primarySchool}"
                bind.primaryMarks.text = "Total marks: ${scholar.primaryMarks.toString()}"
                bind.primaryGrade.text = "Mean-Grade: ${scholar.primaryMeanGrade}"
                bind.branchOrigin.text = "Origin Branch: ${scholar.origin}"
                bind.phone.text = "Primary Number: ${scholar.primaryNumber} \n Other Number: ${scholar.otherNumber}"
                bind.email.text = "Email : ${scholar.emailAddress}"

                if (scholar.pfNumber!!.subSequence(0,2) != "pf"){
                    bind.warning.isVisible = false
                    bind.checkText.text = "${scholar.varsity} \n Course Name: ${scholar.coursesName} \n Grade: ${scholar.varsityGrade}"
                    bind.email.text = "Email : ${scholar.emailAddress}"

                }

            }

        }


        bind.editProfessionalCertifications.setOnClickListener{
            createBottomSheet(inflater)
            bottomSheet.show()

        }
        if(pfNumber!!.subSequence(0,2) == "pf"){
            bind.addProfessionalCertifications.isVisible = false
        }

        bind.addProfessionalCertifications.setOnClickListener{
            createAddBottomSheet(inflater)
            bottomSheet.show()
        }


        return bind.root
    }

    private fun createAddBottomSheet(inflater: LayoutInflater) {
        val view = inflater.inflate(R.layout.add_prof_bottom, null, false)
        val scores = arrayOf("First class Honours", "Second class Honours - Upper Division", "Second class Honours - Lower Division", "Pass")

        //getting views
        val varsity = view.findViewById<TextInputLayout>(R.id.phone)
        val courseName = view.findViewById<TextInputLayout>(R.id.phone1)
        val spinner = view.findViewById<Spinner>(R.id.scholar_id)
        val another = view.findViewById<TextInputLayout>(R.id.email)
        val courseAnotherName = view.findViewById<TextInputLayout>(R.id.course_info)
        val submit = view.findViewById<MaterialButton>(R.id.submit)

        val adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, scores.toList())
        spinner.adapter = adapter

        submit.setOnClickListener{
            if(another.editText!!.text.isBlank() || courseAnotherName.editText!!.text.isBlank()){
                another.error = "Drag the sheet down to exit"
                return@setOnClickListener
            }
            val varsityName = varsity.editText!!.text.trim().toString()
            val varsityCourseName = courseName.editText!!.text.trim().toString()
            val grade = spinner.selectedItem.toString()
            val anotherSchool = another.editText!!.text.trim().toString()
            val anotherSchoolCourseName = courseAnotherName.editText!!.text.trim().toString()

            if (varsityName.isNotBlank() && (varsityCourseName.isBlank() || grade.isBlank())){
                courseName.error = "Please fill all the required fields"
            }else if(varsityName.isBlank() && varsityCourseName.isBlank() && grade.isBlank()){
                scholar.tertiaryInstitutions[anotherSchool] = anotherSchoolCourseName
            }else{
                scholar.varsity = varsityName
                scholar.coursesName = varsityCourseName
                scholar.varsityGrade = grade
                scholar.tertiaryInstitutions[anotherSchool] = anotherSchoolCourseName

            }

            mAuth.child("user").child(scholar.name!!).setValue(scholar)
            Toast.makeText(requireContext(), "Successfully submitted", Toast.LENGTH_LONG).show()
            bind.checkText.text = "$varsityName \n Course Name: $varsityCourseName \n Grade: $grade"

            bottomSheet.dismiss()
        }

        bottomSheet.setContentView(view)

        //setting the bottom sheet behavior
        val behavior = BottomSheetBehavior.from(view.parent as View)
        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        //setting the measurement metrics
        val coordinatorLayout = view.findViewById<ViewGroup>(R.id.coordinate)
        coordinatorLayout.minimumHeight = Resources.getSystem().displayMetrics.heightPixels

    }

    private fun createBottomSheet(inflater: LayoutInflater) {
        val view = inflater.inflate(R.layout.edit_info_dialog, null, false)

        //getting views from the dialog
        val phone = view.findViewById<TextInputLayout>(R.id.phone)
        val otherPhone = view.findViewById<TextInputLayout>(R.id.phone1)
        val email = view.findViewById<TextInputLayout>(R.id.email)
        val submit = view.findViewById<MaterialButton>(R.id.submit)

//        if(scholar.primaryNumber!!.isNotBlank()){
//            phone.editText!!.setText(scholar.primaryNumber)
//        }
//        if(scholar.otherNumber!!.isNotBlank()){
//            otherPhone.editText!!.setText(scholar.otherNumber)
//        }

        submit.setOnClickListener{
            if (phone.editText!!.text.isBlank() || otherPhone.editText!!.text.isBlank() || email.editText!!.text.isBlank()){
                phone.error = "All fields must be filled"
                return@setOnClickListener
            }
            val userPhone = phone.editText!!.text.trim().toString()
            val userOtherPhone = otherPhone.editText!!.text.trim().toString()
            val userEmail = email.editText!!.text.trim().toString()

            scholar.apply {
                primaryNumber = userPhone
                otherNumber = userOtherPhone
                emailAddress = userEmail
            }
            mAuth.child("user").child(scholar.name!!).setValue(scholar)
            bind.phone.text = "Primary Number: $userPhone \n Other Number: $userOtherPhone"
            bind.email.text = "Email : $userEmail"

            Toast.makeText(requireContext(), "Successfully submitted", Toast.LENGTH_LONG).show()
            bottomSheet.dismiss()
        }

        bottomSheet.setContentView(view)

        //setting bottom sheet behaviour
        val behavior = BottomSheetBehavior.from(view.parent as View)
        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        //setting the measurement metrics
        val layout = view.findViewById<ViewGroup>(R.id.coordinate)
        layout.minimumHeight = Resources.getSystem().displayMetrics.heightPixels
    }

    override fun onResume() {
        super.onResume()
        val pfNumber = pref.getString(Constants.PF_NUMBER, null)
        for (databaseScholar in scholars){
            val currentPfNumber = databaseScholar.pfNumber
            if (currentPfNumber.contentEquals(pfNumber, true)){
                scholar = databaseScholar

                bind.highSchool.text = "High-School: ${scholar.secondarySchool}"
                bind.highGrade.text = "Mean-Grade: ${scholar.meanGrade}"
                bind.highMarks.text = "Mean-AGP: ${scholar.meanAGP.toString()}"
                bind.primarySchool.text = "Primary School: ${scholar.primarySchool}"
                bind.primaryMarks.text = "Total marks: ${scholar.primaryMarks.toString()}"
                bind.primaryGrade.text = "Mean-Grade: ${scholar.primaryMeanGrade}"
                bind.branchOrigin.text = "Origin Branch: ${scholar.origin}"
                bind.phone.text = "Primary Number: ${scholar.primaryNumber} \n Other Number: ${scholar.otherNumber}"
                bind.email.text = "Email : ${scholar.emailAddress}"

                if (scholar.pfNumber!!.subSequence(0,2) != "pf"){
                    bind.warning.isVisible = false
                    bind.checkText.text = "${scholar.varsity} \n Course Name: ${scholar.coursesName} \n Grade: ${scholar.varsityGrade}"
                    bind.email.text = "Email : ${scholar.emailAddress}"

                }

            }

        }

    }


}