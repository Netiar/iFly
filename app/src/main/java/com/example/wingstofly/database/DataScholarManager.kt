package com.example.wingstofly.database

import com.example.wingstofly.models.*
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class DataScholarManager {
    val scholars = ArrayList<Scholar>()
    private  val subjects = Subjects().allSubjects
    private val school = School("The Technical university of Kenya").apply {
        schoolType = "university"
        startYear = "02/09/2015"
        endYear = "31/07/2021"
        course = "Business Information Technology"
    }

    private val school1 = School("Moringa School").apply {
        schoolType = "Boot Camp"
        startYear = "02/02/2022"
        endYear = "31/08/2022"
        course = "Software Development"
    }


    init {
        getScholars()
    }

    private fun getScholars(){
        val scholar = Scholar("Charles Muvaka", "Employed")
        scholar.apply {
            origin = "Kericho Branch"
            form = 4
            scholarPfTest = "pfsn"
            pfNumber = "sn41890"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
            primarySchool = "Umoja primary school"
            password = "sn41890"
            primaryMeanGrade = "B+"
            primaryMarks = 404
            primaryMeanAGP = 12
            secondarySchool = "St Josephs Seminary Mwingi"
            skillSet = "Software Developer"

            tertiaryInstitutions.add(school)
            tertiaryInstitutions.add(school1)

            workPlaces.add(Job("Payments Officer", "Equity Bank Ltd").apply {
                postDate = "03/04/2021"
                deadline = "01/02/2022"
            })
            workPlaces.add(Job("ICT attachee", "Ministry of Mining").apply {
                postDate = "01/02/2022"
                deadline = "01/02/2022"
            })
            workPlaces.add(Job("Casual Labourer", "Kifaru Maize Millers").apply {
                postDate = "01/02/2022"
                deadline = "01/02/2022"
            })

            leadershipRoles.add(Event("Football School Captain").apply {
                eventOwner = "St Josephs Seminary"
            })
            leadershipRoles.add(Event("Football School Captain").apply {
                eventOwner = "St Josephs Seminary"
            })
            leadershipRoles.add(Event("Wings to fly Mentor").apply {
                eventOwner = "Equity Bank Ltd"
            })
        }
        scholars.add(scholar)
        val scholar1 = Scholar("Albright Sunguti", "Student")
        scholar1.apply {
            form = 4
            origin = "Changamwe Branch"
            pfNumber = "pf41891"
            primarySchool = "Chaani primary school"
            secondarySchool = "Alliance Boys High School"
            primaryMeanGrade = "B+"
            password = "pf41891"
            primaryMarks = 396
            primaryMeanAGP = 11
            primaryNumber = "0713648750"
        }
        scholars.add(scholar1)
        val scholar2 = Scholar("Rose Chari", "Employed")
        scholar2.apply {
            form = 4
            origin = "Moi-Avenue Branch"
            pfNumber = "pf41892"
            primarySchool = "Nairobi primary school"
            secondarySchool = "Starehe Boys Centre"
        }
        scholars.add(scholar2)
        val scholar3= Scholar("Fahmy Nassir", "Unemployed")
        scholar3.apply {
            form = 4
            origin = "Digo-Road Branch"
            pfNumber = "sn81898"
            primarySchool = "Wayani Hill Academy"
            secondarySchool = "Precious blood Riruta"
        }
        scholars.add(scholar3)
        val scholar4 = Scholar("Francis Kalama", "Student")
        scholar4.apply {
            form = 4
            origin = "Subukia Branch"
            pfNumber = "pf41894"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
        }
        scholars.add(scholar4)
        val scholar5 = Scholar("Glen Jonhson", "Student")
        scholar5.apply {
            pfNumber = "pf81897"
            form = 3
            origin ="Kericho Branch"
            primarySchool = "Umoja primary school"
            secondarySchool = "St Josephs Seminary Mwingi"
            varsity = "The Technical University of Kenya"
        }
        scholars.add(scholar5)
        val scholar6 = Scholar("Steven Gerrard", "Student")
        scholar6.apply {
            pfNumber = "pf81896"
            form = 2
            origin = "Changamwe Branch"
            primarySchool = "Chaani primary school"
            secondarySchool = "Alliance Boys High School"
        }
        scholars.add(scholar6)
        val scholar7 = Scholar("Dirk Kuyt", "Student")
        scholar7.apply {
            pfNumber = "pf81895"
            form = 1
            origin = "Moi-Avenue Branch"
            primarySchool = "Nairobi primary school"
            secondarySchool = "Alliance Boys High School"
        }
        scholars.add(scholar7)
        val scholar8 = Scholar("Leo Messi", "Student")
        scholar8.apply {
            origin = "Digo-Road Branch"
            pfNumber = "pf81894"
            form = 4
            primarySchool = "Wayani Hill Academy"
            secondarySchool = "Precious blood Riruta"
        }
        scholars.add(scholar8)
        val scholar9 = Scholar("Naby Keita", "Student")
        scholar9.apply {
            origin = "Subukia Branch"
            pfNumber = "pf81893"
            form = 4
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
        }
        scholars.add(scholar9)
        val scholar10 = Scholar("ALice Elias", "Student")
        scholar10.apply {
            origin = "Kericho Branch"
            pfNumber = "pf81892"
            form = 3
            primarySchool = "Umoja primary school"
            secondarySchool = "Alliance Boys High School"
            varsity = "The Technical University of Kenya"
        }
        scholars.add(scholar10)
        val scholar11 = Scholar("Riggy Gachagua", "Student")
        scholar11.apply {
            origin  = "Changamwe Branch"
            pfNumber = "pf81891"
            form = 2
            primarySchool = "Chaani primary school"
            secondarySchool = "Alliance Boys High School"
        }
        scholars.add(scholar11)
        val scholar12 = Scholar("Uhuru Kenyatta", "Student")
        scholar12.apply {
            origin = "Moi-Avenue Branch"
            pfNumber = "pf81890"
            form = 1
            primarySchool = "Nairobi primary school"
            secondarySchool = "Starehe Boys Centre"
        }
        scholars.add(scholar12)
        val scholar13 = Scholar("Raila Amollo", "Student")
        scholar13.apply {
            origin = "Digo-Road Branch"
            pfNumber = "pf71899"
            form = 4
            primarySchool = "Wayani Hill Academy"
            secondarySchool = "Precious blood Riruta"
        }
        scholars.add(scholar13)
        val scholar14 = Scholar("Regina Chao", "Student")
        scholar14.apply {
            origin = "Subukia Branch"
            pfNumber = "pf71898"
            form = 3
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
        }
        scholars.add(scholar14)
        val scholar15 = Scholar("Paul Gitonga", "Student")
        scholar15.apply {
            origin = "Kericho Branch"
            pfNumber = "pf71897"
            form = 2
            primarySchool = "Umoja primary school"
            secondarySchool = "St Josephs Seminary Mwingi"
            varsity = "The Technical University of Kenya"
        }
        scholars.add(scholar15)

        //Employed and non-employed students

        val scholar16 = Scholar("Gideon Masha", "Employed")
        scholar16.apply {
            origin = "Kericho Branch"
            pfNumber = "pf71896"
            form = 1
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
            primarySchool = "Umoja primary school"
            primaryMeanGrade = "B+"
            primaryMarks = 364
            primaryMeanAGP = 10
            secondarySchool = "St Josephs Seminary Mwingi"
            varsity = "The Technical University of Kenya"
        }
        scholars.add(scholar16)
        val scholar17 = Scholar("Ian Miheno", "Unemployed")
        scholar17.apply {
            origin = "Changamwe Branch"
            pfNumber = "pf71895"
            form = 4
            primarySchool = "Chaani primary school"
            secondarySchool = "Alliance Boys High School"
            primaryMarks = 364
            primaryMeanAGP = 10
            varsity = "The Technical University of Kenya"
            primaryMeanGrade = "B+"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"

        }
        scholars.add(scholar17)
        val scholar18 = Scholar("Michael Ratego", "Employed")
        scholar18.apply {
            origin = "Moi-Avenue Branch"
            pfNumber = "pf71894"
            form = 3
            primarySchool = "Nairobi primary school"
            secondarySchool = "Starehe Boys Centre"
            primaryMarks = 364
            primaryMeanAGP = 10
            varsity = "The Technical University of Kenya"
            primaryMeanGrade = "B+"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
        }
        scholars.add(scholar18)
        val scholar19= Scholar("Salim AbdulNassir", "Unemployed")
        scholar19.apply {
            origin = "Digo-Road Branch"
            pfNumber = "pf71893"
            form = 2
            primarySchool = "Wayani Hill Academy"
            secondarySchool = "Precious blood Riruta"
            primaryMarks = 364
            primaryMeanAGP = 10
            varsity = "The Technical University of Kenya"
            primaryMeanGrade = "B+"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
        }
        scholars.add(scholar19)
        val scholar20 = Scholar("Joshua Ndemwa", "Employed")
        scholar20.apply {
            origin = "Subukia Branch"
            pfNumber = "pf71894"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
            primaryMarks = 364
            primaryMeanAGP = 10
            varsity = "The Technical University of Kenya"
            primaryMeanGrade = "B+"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
            form = 1
        }
        scholars.add(scholar20)
        val scholar21 = Scholar("Maalim Ndemwa", "Employed")
        scholar21.apply {
            origin = "Subukia Branch"
            pfNumber = "pf91894"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
            primaryMarks = 364
            primaryMeanAGP = 10
            varsity = "The Technical University of Kenya"
            primaryMeanGrade = "B+"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
            form = 1
        }
        scholars.add(scholar21)
        val scholar22 = Scholar("Nina Wendy", "Employed")
        scholar22.apply {
            origin = "Subukia Branch"
            pfNumber = "pf91895"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
            primaryMarks = 364
            primaryMeanAGP = 10
            varsity = "The Technical University of Kenya"
            primaryMeanGrade = "B+"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
            form = 1
        }
        scholars.add(scholar22)
        val scholar23 = Scholar("Johan Musumbi", "Employed")
        scholar23.apply {
            origin = "Subukia Branch"
            pfNumber = "pf91896"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
            primaryMarks = 364
            primaryMeanAGP = 10
            varsity = "The Technical University of Kenya"
            primaryMeanGrade = "B+"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
            form = 1
        }
        scholars.add(scholar23)

        val scholar24 = Scholar("Nicholas Tanui", "Employed")
        scholar24.apply {
            origin = "Subukia Branch"
            pfNumber = "pf91896"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
            primaryMarks = 364
            primaryMeanAGP = 10
            varsity = "The Technical University of Kenya"
            primaryMeanGrade = "B+"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
            form = 1
        }
        scholars.add(scholar24)

        val scholar25 = Scholar("Nicholas Tanui", "Employed")
        scholar25.apply {
            origin = "Subukia Branch"
            pfNumber = "pf91897"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
            primaryMarks = 364
            primaryMeanAGP = 10
            varsity = "The Technical University of Kenya"
            primaryMeanGrade = "B+"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
            form = 1
        }
        scholars.add(scholar25)
        val scholar26 = Scholar("Nicholas Tanui", "Employed")
        scholar26.apply {
            origin = "Subukia Branch"
            pfNumber = "pf91897"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
            primaryMarks = 364
            primaryMeanAGP = 10
            varsity = "The Technical University of Kenya"
            primaryMeanGrade = "B+"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
            form = 1
        }
        scholars.add(scholar26)

    }
}