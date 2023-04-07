package com.example.wingstofly.fragments

import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.adapters.JobDetailsRecView
import com.example.wingstofly.adapters.JobsRecView
import com.example.wingstofly.adapters.SchoolAdapter
import com.example.wingstofly.databinding.FragmentEditInfoBinding
import com.example.wingstofly.models.*
import com.example.wingstofly.utils.Constants
import com.example.wingstofly.utils.Validator
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.*


class EditInfoFragment : Fragment(), View.OnClickListener {
    private lateinit var mAuth: DatabaseReference
    private lateinit var scholars: ArrayList<Scholar>
    private lateinit var bind: FragmentEditInfoBinding
    private lateinit var pref: SharedPreferences
    private lateinit var bottomSheet: BottomSheetDialog
    var scholar1: Scholar? = null


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

        //starting Animations
        startAnimations()


        mAuth = FirebaseDatabase.getInstance().reference
        pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        scholars = (activity as MainActivity).scholars
        val pfNumber = pref.getString(Constants.PF_NUMBER, null)


        for (databaseScholar in scholars) {
            val currentPfNumber = databaseScholar.pfNumber
            if (currentPfNumber.contentEquals(pfNumber, true)) {
                scholar1 = databaseScholar
                val scholar = scholar1!!

                //binding the scholar phone details
                bind.primarySchool.text = "Phone: ${scholar.primaryNumber}"
                bind.primaryMarks.text = "Other phone: ${scholar.otherNumber}"
                bind.highSchool.text = "Email: ${scholar.emailAddress}"

                val school = School(scholar.secondarySchool!!).apply {
                    course = "High School Certification"
                    startYear = "08/02/2015"
                    endYear = "02/11/2014"
                }
                val school1 = School(scholar.primarySchool!!).apply {
                    course = "Junior Studies Certification"
                    startYear = "08/01/2003"
                    endYear = "02/11/2010"
                }

                val listSchools = ArrayList<School>()
                listSchools.add(school)
                listSchools.add(school1)
                if (scholar.tertiaryInstitutions.size == 0) {
                    val schoolAdapter = SchoolAdapter(listSchools)
                    bind.recView.apply {
                        adapter = schoolAdapter
                        layoutManager =
                            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                    }

                } else {
                    for (school in scholar.tertiaryInstitutions) {
                        listSchools.add(school)
                    }
                    val schoolAdapter = SchoolAdapter(listSchools)
                    bind.recView.apply {
                        adapter = schoolAdapter
                        layoutManager =
                            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                    }
                }

                if (scholar.workPlaces.isEmpty()) {
                    bind.checkText.text = "Not updated"
                } else {
                    bind.warning.isVisible = false
                    bind.checkText.isVisible = false
                    bind.recProfessionalInfo.isVisible = true
                    val jobAdapter = JobsRecView(requireContext())
                    jobAdapter.asyncList.submitList(scholar.workPlaces)
                    bind.recProfessionalInfo.apply {
                        adapter = jobAdapter
                        layoutManager =
                            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                    }
                }

                //dummy data on empty leader ship roles
                val string1 = "Update to help build your resume"
                val string2 = "Update to help build your resume"
                val string3 = "Update to help build your resume"

                val leaders = ArrayList<String>()

                if (scholar.leadershipRoles.isEmpty()) {
                    leaders.add(string1)
                    leaders.add(string2)
                    leaders.add(string3)
                    bind.leadersRec.apply {
                        adapter = JobDetailsRecView(leaders)
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                } else if (scholar.leadershipRoles.size == 1) {
                    val string = "${scholar.leadershipRoles[0].title} - ${scholar.leadershipRoles[0].eventOwner}"
                    leaders.add(string)
                    leaders.add(string1)
                    leaders.add(string2)


                    bind.leadersRec.apply {
                        adapter = JobDetailsRecView(leaders)
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                } else if (scholar.leadershipRoles.size == 2) {
                    for (i in 0 until 2) {
                        val string = "${scholar.leadershipRoles[i].title} - ${scholar.leadershipRoles[i].eventOwner}"
                        leaders.add(string)
                    }
                    leaders.add(string1)

                    bind.leadersRec.apply {
                        adapter = JobDetailsRecView(leaders)
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                } else {
                    val list = ArrayList<String>()
                    for (i in scholar.leadershipRoles.size - 1    downTo  scholar.leadershipRoles.size - 3) {
                        val string = "${scholar.leadershipRoles[i].title} - ${scholar.leadershipRoles[i].eventOwner}"
                        list.add(string)
                    }
                    val leaders = JobDetailsRecView(list)
                    bind.leadersRec.apply {
                        adapter = leaders
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            }
        }

        bind.skillSet.setOnClickListener(this::onClick)
        bind.addProfessionalCertifications.setOnClickListener(this::onClick)
        bind.addLeadership.setOnClickListener(this::onClick)
        bind.resume.setOnClickListener(this::onClick)
        bind.quitPage.setOnClickListener(this::onClick)
        bind.viewAnother.setOnClickListener(this::onClick)

        bind.update.setOnClickListener {
            createBottomSheet(inflater)
            bottomSheet.show()

        }

        bind.editProfessionalCertifications.setOnClickListener {
            createAddBottomSheet(inflater)
            bottomSheet.show()
        }

        return bind.root
    }

    private fun startAnimations() {
        bind.primarySchool.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            )
        )
        bind.primaryMarks.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            )
        )
        bind.highSchool.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(), R.anim.zoom_in
            )
        )
    }

    private fun createAddBottomSheet(inflater: LayoutInflater) {
        val scholar = scholar1!!

        val view = inflater.inflate(R.layout.add_prof_bottom, null, false)
        val scores = arrayOf(
            "First class Honours",
            "Second class Honours - Upper Division",
            "Second class Honours - Lower Division",
            "Pass"
        )

        //getting views
        val varsity = view.findViewById<TextInputLayout>(R.id.phone)
        val courseName = view.findViewById<TextInputLayout>(R.id.phone1)
        val spinner = view.findViewById<Spinner>(R.id.scholar_id)
        val another = view.findViewById<TextInputLayout>(R.id.email)
        val courseAnotherName = view.findViewById<TextInputLayout>(R.id.course_info)
        val submit = view.findViewById<MaterialButton>(R.id.submit)

        val adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, scores.toList())
        spinner.adapter = adapter

        submit.setOnClickListener {
            if (another.editText!!.text.isBlank() || courseAnotherName.editText!!.text.isBlank()) {
                another.error = "Drag the sheet down to exit"
                return@setOnClickListener
            }
            val varsityName = varsity.editText!!.text.trim().toString()
            val varsityCourseName = courseName.editText!!.text.trim().toString()
            val grade = spinner.selectedItem.toString()
            val anotherSchool = another.editText!!.text.trim().toString()
            val anotherSchoolCourseName = courseAnotherName.editText!!.text.trim().toString()

            if (varsityName.isNotBlank() && (varsityCourseName.isBlank() || grade.isBlank())) {
                courseName.error = "Please fill all the required fields"
            } else if (varsityName.isBlank() && varsityCourseName.isBlank() && grade.isBlank()) {
//                scholar.tertiaryInstitutions[anotherSchool] = anotherSchoolCourseName
            } else {
                scholar.varsity = varsityName
                scholar.coursesName = varsityCourseName
                scholar.varsityGrade = grade
//                scholar.tertiaryInstitutions[anotherSchool] = anotherSchoolCourseName

            }

            mAuth.child("scholars").child(scholar.name!!).setValue(scholar)
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

        submit.setOnClickListener {
            if (phone.editText!!.text.isBlank() || otherPhone.editText!!.text.isBlank() || email.editText!!.text.isBlank()) {
                phone.error = "All fields must be filled"
                return@setOnClickListener
            }
            val userPhone = phone.editText!!.text.toString().trim()
            val userOtherPhone = otherPhone.editText!!.text.toString().trim()
            val userEmail = email.editText!!.text.toString().trim()
            val scholar = scholar1!!

            scholar.primaryNumber = userPhone
            scholar.otherNumber = userOtherPhone
            scholar.emailAddress = userEmail

            bind.primarySchool.text = "Phone: $userPhone"
            bind.primaryMarks.text = "Other: $userOtherPhone"
            bind.highSchool.text = "Email: $userEmail"

            mAuth.child("scholars").child(scholar.name!!).setValue(scholar)

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

    override fun onClick(p0: View?) {
        if (p0 == bind.skillSet) {
            bind.skills.isVisible = true
            bind.recView.alpha = .2f
            bind.transform.startTransform()

            val list = Constants.skillSets
            bind.radioAns1.text = list[0]
            bind.radioAns2.text = list[1]
            bind.radioAns3.text = list[2]
            bind.radioAns4.text = list[3]

            bind.submit2.setOnClickListener {
                var skillSet: String? = null
                if (bind.radioAns1.isChecked) {
                    skillSet = bind.radioAns1.text as String
                } else if (bind.radioAns2.isChecked) {
                    skillSet = bind.radioAns2.text as String

                } else if (bind.radioAns3.isChecked) {
                    skillSet = bind.radioAns3.text as String
                } else if (bind.radioAns4.isChecked) {
                    skillSet = bind.radioAns4.text as String
                } else {
                    Toast.makeText(requireContext(), "Please select a skill set", Toast.LENGTH_LONG)
                        .show()
                }
                val scholar = scholar1!!

                scholar.skillSet = skillSet
                bind.skills.isVisible = false
                mAuth.child("scholars").child(scholar.name!!).setValue(scholar)
                bind.transform.finishTransform()
                bind.recView.alpha = 1.0f
            }
            bind.card.setOnClickListener {
                bind.transform.finishTransform()
                bind.skills.isVisible = false
                bind.recView.alpha = 1.0f

            }

        }
        if (p0 == bind.addProfessionalCertifications) {
            bind.empRoles.isVisible = true
            bind.recView.alpha = .2f
            bind.transform1.startTransform()

            bind.submit1.setOnClickListener {
                if (Validator().validateInputText(bind.roleName) || Validator().validateInputText(
                        bind.roleId
                    )
                    || Validator().validateInputText(bind.startId) || Validator().validateInputText(
                        bind.endId
                    )
                ) {
                    return@setOnClickListener
                } else {
                    val name = bind.roleName.editText!!.text.trim().toString()
                    val place = bind.roleId.editText!!.text.trim().toString()
                    val start = bind.startId.editText!!.text.trim().toString()
                    val end = bind.endId.editText!!.text.trim().toString()

                    val newJob = Job(name, place).apply {
                        postDate = start
                        deadline = end
                    }
                    val scholar = scholar1!!

                    scholar.workPlaces.add(newJob)
                    bind.empRoles.isVisible = false
                    bind.transform1.finishTransform()
                    bind.recView.alpha = 1.0f

                }


            }
            bind.card.setOnClickListener {
                bind.transform1.finishTransform()
                bind.empRoles.isVisible = false
                bind.recView.alpha = 1.0f

            }
        }
        if (p0 == bind.addLeadership) {
            bind.leaderRoles.isVisible = true
            bind.recView.alpha = .2f

            bind.transform2.startTransform()

            bind.submit.setOnClickListener {
                if (!Validator().validateInputText(bind.firstName) || !Validator().validateInputText(
                        bind.scholarId
                    )
                ) {
                    return@setOnClickListener
                } else {
                    val leaderRoleName = bind.firstName.editText!!.text.trim().toString()
                    val leaderRolePlace = bind.scholarId.editText!!.text.trim().toString()

                    val event = Event(leaderRoleName).apply {
                        eventOwner = leaderRolePlace
                    }
                    val scholar = scholar1!!

                    scholar.leadershipRoles.add(event)
                    mAuth.child("scholars").child(scholar.name!!).setValue(scholar)
                    Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_SHORT)
                        .show()

                    bind.transform2.finishTransform()
                    bind.leaderRoles.isVisible = false
                    bind.recView.alpha = 1.0f

                }

            }
            bind.card.setOnClickListener {
                bind.transform2.finishTransform()
                bind.leaderRoles.isVisible = false
                bind.recView.alpha = 1.0f

            }

        }
        val scholar = scholar1!!

        if (p0 == bind.resume) {
            val appearance = "Portrait"
            val fragment = NewsFragment.newInstance(scholar, appearance)
            replaceFragment(fragment)
            bind.transform3.startTransform()
        }
        if (p0 == bind.quitPage) {
            bind.transform3.finishTransform()
        }
        if (p0 == bind.viewAnother) {
            val currentText = bind.viewAnother.text
            val fragment = NewsFragment.newInstance(scholar, "Landscape")
            replaceFragment(fragment)
            bind.viewAnother.text = "Landscape"

            if (currentText == "Landscape") {
                val fragment = NewsFragment.newInstance(scholar, "Portrait")
                replaceFragment(fragment)
                bind.viewAnother.text = "Portrait"

            }
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val manager = childFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(bind.frameLayout.id, fragment).commit()
    }

}




