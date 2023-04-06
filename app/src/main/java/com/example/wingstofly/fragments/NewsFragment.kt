package com.example.wingstofly.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.wingstofly.adapters.JobDetailsRecView
import com.example.wingstofly.adapters.ResumeAdapter
import com.example.wingstofly.databinding.ActivityNewsBinding
import com.example.wingstofly.databinding.ActivityNewsRecviewBinding
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.models.School

class NewsFragment : Fragment(), View.OnClickListener {
    private lateinit var mainBind: ViewBinding
    private var scholar: Scholar? = null
    private var appearance: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            scholar = it.getSerializable("event") as Scholar?
            appearance = it.getString("appearance")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //scholar name and origin
        val names = scholar!!.name!!.split(" ")
        val branch = scholar!!.origin!!.split(" ")

        //adapters
        val educationAdapter = ResumeAdapter(requireContext())
        val refAdapter = ResumeAdapter(requireContext())



        //getting scholars junior and high school info
        val school = School(scholar!!.primarySchool!!).apply {
            course = "Junior studies Certification"
            startYear = "08/01/2003"
            endYear = "08/11/2010"
        }
        val school1 = School(scholar!!.secondarySchool!!).apply {
            course = "High School Certification"
            startYear = "08/02/2011"
            endYear = "08/11/2014"
        }

        //Generating dummy referees
        val referee = School("John Doe").apply {
            course = "Branch Growth and Development Manager"
            startYear = "0768761610"
            endYear = "muvakacharles@gmail.com"
        }
        val referee1 = School("Charles Muvaka").apply {
            course = "Regional Manager Payments"
            startYear = "0768761610"
            endYear = "muvaka@gmail.com"
        }
        val referee2 = School("Mary Atieno").apply {
            course = "General Manager"
            startYear = "0768761610"
            endYear = "charles@gmail.com"
        }
        val refList = arrayListOf(referee, referee1, referee2)
        refAdapter.list = refList

        if (appearance == "Portrait") {
            mainBind = ActivityNewsRecviewBinding.inflate(inflater)
            val bind = mainBind

            //binding name and contact details

            (bind as ActivityNewsRecviewBinding).name.text =
                "${names!![0]} ${names[names.size - 1]}"
            bind.box.text = scholar!!.primaryNumber
            bind.place.text = "${branch[0]}, Kenya"

            //binding education details

            educationAdapter.placeUsed = "Resume"
            educationAdapter.list = scholar!!.tertiaryInstitutions
            educationAdapter.list!!.add(school1)
            educationAdapter.list!!.add(school)
            bind.educationRec.apply {
                adapter = educationAdapter
                layoutManager = LinearLayoutManager(requireContext())

            }

            //binding employment details
            val employmentAdapter = ResumeAdapter(requireContext())
            employmentAdapter.placeUsed = "Resume"
            employmentAdapter.list1 = scholar!!.workPlaces
            bind.employmentRec.apply {
                adapter = employmentAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }


            val list = ArrayList<String>()

            //resume leadership roles
            if (scholar!!.leadershipRoles.size != 0) {
                for (role in scholar!!.leadershipRoles) {
                    val roleName = role.title
                    list.add(roleName!!)
                }
            } else {
                val roleName = "1. Update your info"
                val roleName1 = "2. Opportunities awaiting"
                val roleName2 = "3. Opportunities awaiting"
                list.add(roleName)
                list.add(roleName1)
                list.add(roleName2)
            }
            val leaderAdapter = JobDetailsRecView(list)
            leaderAdapter.placeUsed = "Resume"
            bind.leadershipRec.apply {
                adapter = leaderAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

            //portrait referees binding
            refAdapter.placeUsed = "referees"
            bind.refereesRec.apply {
                adapter = refAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

        } else {
            mainBind = ActivityNewsBinding.inflate(layoutInflater)

            //binding name and contact details

            (mainBind as ActivityNewsBinding).name.text = "${names[0]} ${names[names.size - 1]}"
            (mainBind as ActivityNewsBinding).box.apply {
                text = "Phone: ${scholar!!.primaryNumber}"
            }
            (mainBind as ActivityNewsBinding).place.text = "${branch[0]}, Kenya"

            //binding education details

            educationAdapter.placeUsed = "Landscape"
            educationAdapter.list = scholar!!.tertiaryInstitutions
            educationAdapter.list!!.add(school1)
            educationAdapter.list!!.add(school)
            (mainBind as ActivityNewsBinding).educationRec.apply {
                adapter = educationAdapter
                layoutManager = LinearLayoutManager(requireContext())

            }

            //binding employment details
            val employmentAdapter = ResumeAdapter(requireContext())
            employmentAdapter.placeUsed = "Landscape"
            employmentAdapter.list1 = scholar!!.workPlaces
            (mainBind as ActivityNewsBinding).employmentRec.apply {
                adapter = employmentAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }


            val list = ArrayList<String>()

            //resume leadership roles
            if (scholar!!.leadershipRoles.size != 0) {
                for (role in scholar!!.leadershipRoles) {
                    val roleName = role.title
                    list.add(roleName!!)
                }
            } else {
                val roleName = "1. Update your info"
                val roleName1 = "2. Opportunities awaiting"
                val roleName2 = "3. Opportunities awaiting"
                list.add(roleName)
                list.add(roleName1)
                list.add(roleName2)
            }
            val leaderAdapter = JobDetailsRecView(list)
            leaderAdapter.placeUsed = "Landscape"
            (mainBind as ActivityNewsBinding).leadershipRec.apply {
                adapter = leaderAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

            //landscape referees binding
            refAdapter.list = refList

            (mainBind as ActivityNewsBinding).refereesRec.apply {
                adapter = refAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
        return mainBind.root
    }

    companion object {
        fun newInstance(scholar: Scholar, appearance: String): NewsFragment {
            val args = Bundle().apply {
                putSerializable("event", scholar)
                putString("appearance", appearance)
            }
            val fragment = NewsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onClick(p0: View?) {

    }
}