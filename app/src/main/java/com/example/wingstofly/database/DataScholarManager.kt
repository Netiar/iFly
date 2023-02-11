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
            setOrigin("Kericho Branch")
            pfNumber = "sn41890"
            primaryNumber = "0768761610"
            otherNumber = "0114830685"
            primarySchool = "Umoja primary school"
            primaryMeanGrade = "B+"
            primaryMarks = 364
            primaryMeanAGP = 10
            secondarySchool = "St Josephs Seminary Mwingi"
            varsity = "The Technical University of Kenya"
        }
        scholars.add(scholar)
        val scholar1 = Scholar("Albright Sunguti", "Student")
        scholar1.apply {
            setOrigin("Changamwe Branch")
            pfNumber = "pf41891"
            primarySchool = "Chaani primary school"
            secondarySchool = "Alliance Boys High School"
        }
        scholars.add(scholar1)
        val scholar2 = Scholar("Rose Chari", "Student")
        scholar2.apply {
            setOrigin("Moi-Avenue Branch")
            pfNumber = "pf41892"
            primarySchool = "Nairobi primary school"
            secondarySchool = "Starehe Boys Centre"
        }
        scholars.add(scholar2)
        val scholar3= Scholar("Fahmy Nassir", "Student")
        scholar3.apply {
            setOrigin("Digo-Road Branch")
            pfNumber = "pf41893"
            primarySchool = "Wayani Hill Academy"
            secondarySchool = "Precious blood Riruta"
        }
        scholars.add(scholar3)
        val scholar4 = Scholar("Francis Kalama", "Student")
        scholar4.apply {
            setOrigin("Subukia Branch")
            pfNumber = "pf41894"
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
        }
        scholars.add(scholar4)
        val scholar5 = Scholar("Glen Jonhson", "Student")
        scholar5.apply {
            setOrigin("Kericho Branch")
            primarySchool = "Umoja primary school"
            secondarySchool = "St Josephs Seminary Mwingi"
            varsity = "The Technical University of Kenya"
        }
        scholars.add(scholar5)
        val scholar6 = Scholar("Steven Gerrard", "Student")
        scholar6.apply {
            setOrigin("Changamwe Branch")
            primarySchool = "Chaani primary school"
            secondarySchool = "Alliance Boys High School"
        }
        scholars.add(scholar6)
        val scholar7 = Scholar("Dirk Kuyt", "Student")
        scholar7.apply {
            setOrigin("Moi-Avenue Branch")
            primarySchool = "Nairobi primary school"
            secondarySchool = "Starehe Boys Centre"
        }
        scholars.add(scholar7)
        val scholar8 = Scholar("Leo Messi", "Student")
        scholar8.apply {
            setOrigin("Digo-Road Branch")
            primarySchool = "Wayani Hill Academy"
            secondarySchool = "Precious blood Riruta"
        }
        scholars.add(scholar8)
        val scholar9 = Scholar("Naby Keita", "Student")
        scholar9.apply {
            setOrigin("Subukia Branch")
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
        }
        scholars.add(scholar9)
        val scholar10 = Scholar("Charles Muvaka", "Student")
        scholar10.apply {
            setOrigin("Kericho Branch")
            primarySchool = "Umoja primary school"
            secondarySchool = "St Josephs Seminary Mwingi"
            varsity = "The Technical University of Kenya"
        }
        scholars.add(scholar10)
        val scholar11 = Scholar("Riggy Gachagua", "Student")
        scholar11.apply {
            setOrigin("Changamwe Branch")
            primarySchool = "Chaani primary school"
            secondarySchool = "Alliance Boys High School"
        }
        scholars.add(scholar11)
        val scholar12 = Scholar("Uhuru Kenyatta", "Student")
        scholar12.apply {
            setOrigin("Moi-Avenue Branch")
            primarySchool = "Nairobi primary school"
            secondarySchool = "Starehe Boys Centre"
        }
        scholars.add(scholar12)
        val scholar13 = Scholar("Raila Amollo", "Student")
        scholar13.apply {
            setOrigin("Digo-Road Branch")
            primarySchool = "Wayani Hill Academy"
            secondarySchool = "Precious blood Riruta"
        }
        scholars.add(scholar13)
        val scholar14 = Scholar("Regina Chao", "Student")
        scholar14.apply {
            setOrigin("Subukia Branch")
            primarySchool = "Gome Primary school"
            secondarySchool = "Nairobi School"
        }
        scholars.add(scholar14)
        val scholar15 = Scholar("Paul Gitonga", "Student")
        scholar15.apply {
            setOrigin("Kericho Branch")
            primarySchool = "Umoja primary school"
            secondarySchool = "St Josephs Seminary Mwingi"
            varsity = "The Technical University of Kenya"
        }
        scholars.add(scholar15)
    }
}