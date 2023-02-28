package com.example.wingstofly.database

import com.example.wingstofly.models.Scholar

class DataScholarManager {
    val scholars = ArrayList<Scholar>()

    init {
        getScholars()
    }

    private fun getScholars(){
        val scholar = Scholar("Charles Muvaka", "Student")
        scholar.apply {
            origin = "Kericho Branch"
            pfNumber = "sn41890"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
            primarySchool = "Umoja primary school"
            primaryMeanGrade = "B+"
            primaryMarks = 404
            primaryMeanAGP = 12
            secondarySchool = "St Josephs Seminary Mwingi"
            varsity = "The Technical University of Kenya"
        }
        scholars.add(scholar)
        val scholar1 = Scholar("Albright Sunguti", "Student")
        scholar1.apply {
            origin = "Changamwe Branch"
            pfNumber = "pf41891"
            primarySchool = "Chaani primary school"
            secondarySchool = "Alliance Boys High School"
        }
        scholars.add(scholar1)
        val scholar2 = Scholar("Rose Chari", "Student")
        scholar2.apply {
            origin = "Moi-Avenue Branch"
            pfNumber = "pf41892"
            primarySchool = "Nairobi primary school"
            secondarySchool = "Starehe Boys Centre"
        }
        scholars.add(scholar2)
        val scholar3= Scholar("Fahmy Nassir", "Student")
        scholar3.apply {
            origin = "Digo-Road Branch"
            pfNumber = "pf41893"
            primarySchool = "Wayani Hill Academy"
            secondarySchool = "Precious blood Riruta"
        }
        scholars.add(scholar3)
        val scholar4 = Scholar("Francis Kalama", "Student")
        scholar4.apply {
            origin = "Subukia Branch"
            pfNumber = "pf41894"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
        }
        scholars.add(scholar4)
        val scholar5 = Scholar("Glen Jonhson", "Student")
        scholar5.apply {
            pfNumber = "sn42890"
            origin ="Kericho Branch"
            primarySchool = "Umoja primary school"
            secondarySchool = "St Josephs Seminary Mwingi"
            varsity = "The Technical University of Kenya"
        }
        scholars.add(scholar5)
        val scholar6 = Scholar("Steven Gerrard", "Student")
        scholar6.apply {
            pfNumber = "sn43890"
            origin = "Changamwe Branch"
            primarySchool = "Chaani primary school"
            secondarySchool = "Alliance Boys High School"
        }
        scholars.add(scholar6)
        val scholar7 = Scholar("Dirk Kuyt", "Student")
        scholar7.apply {
            pfNumber = "sn44890"
            origin = "Moi-Avenue Branch"
            primarySchool = "Nairobi primary school"
            secondarySchool = "Alliance Boys High School"
        }
        scholars.add(scholar7)
        val scholar8 = Scholar("Leo Messi", "Student")
        scholar8.apply {
            origin = "Digo-Road Branch"
            pfNumber = "sn61890"
            primarySchool = "Wayani Hill Academy"
            secondarySchool = "Precious blood Riruta"
        }
        scholars.add(scholar8)
        val scholar9 = Scholar("Naby Keita", "Student")
        scholar9.apply {
            origin = "Subukia Branch"
            pfNumber = "sn45890"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
        }
        scholars.add(scholar9)
        val scholar10 = Scholar("ALice Elias", "Student")
        scholar10.apply {
            origin = "Kericho Branch"
            pfNumber = "sn46890"
            primarySchool = "Umoja primary school"
            secondarySchool = "Alliance Boys High School"
            varsity = "The Technical University of Kenya"
        }
        scholars.add(scholar10)
        val scholar11 = Scholar("Riggy Gachagua", "Student")
        scholar11.apply {
            origin  = "Changamwe Branch"
            pfNumber = "sn47890"
            primarySchool = "Chaani primary school"
            secondarySchool = "Alliance Boys High School"
        }
        scholars.add(scholar11)
        val scholar12 = Scholar("Uhuru Kenyatta", "Student")
        scholar12.apply {
            origin = "Moi-Avenue Branch"
            pfNumber = "sn48890"
            primarySchool = "Nairobi primary school"
            secondarySchool = "Starehe Boys Centre"
        }
        scholars.add(scholar12)
        val scholar13 = Scholar("Raila Amollo", "Student")
        scholar13.apply {
            origin = "Digo-Road Branch"
            pfNumber = "sn49890"
            primarySchool = "Wayani Hill Academy"
            secondarySchool = "Precious blood Riruta"
        }
        scholars.add(scholar13)
        val scholar14 = Scholar("Regina Chao", "Student")
        scholar14.apply {
            origin = "Subukia Branch"
            pfNumber = "sn63890"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
        }
        scholars.add(scholar14)
        val scholar15 = Scholar("Paul Gitonga", "Student")
        scholar15.apply {
            origin = "Kericho Branch"
            pfNumber = "sn64890"
            primarySchool = "Umoja primary school"
            secondarySchool = "St Josephs Seminary Mwingi"
            varsity = "The Technical University of Kenya"
        }
        scholars.add(scholar15)

        //Employed and non-employed students

        val scholar16 = Scholar("Gideon Masha", "Employed")
        scholar16.apply {
            origin = "Kericho Branch"
            pfNumber = "sn51890"
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
            pfNumber = "sn51891"
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
            pfNumber = "sn51892"
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
            pfNumber = "sn51893"
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
            pfNumber = "sn51894"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
            primaryMarks = 364
            primaryMeanAGP = 10
            varsity = "The Technical University of Kenya"
            primaryMeanGrade = "B+"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
        }
        scholars.add(scholar20)
    }
}