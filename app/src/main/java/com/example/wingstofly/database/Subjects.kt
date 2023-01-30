package com.example.wingstofly.database

import com.example.wingstofly.models.Subject

class Subjects {
    val allSubjects = ArrayList<Subject>()
    private var subjectNames = arrayListOf(
        "Math",
        "English",
        "Swahili",
        "Physics",
        "Chemistry",
        "Biology",
        "History",
        "Geography",
        "CRE",
        "IRE",
        "Agriculture",
        "Computer",
        "Business"
    )

    private var categories = arrayListOf(
        "Common", "Humanities", "Sciences", "BusinessRelated"
    )

    init {
        getSubjects()
    }

    private fun getSubjects() {
        val math = Subject(subjectNames[0], category = categories[0])
        val english = Subject(subjectNames[1], category = categories[0])
        val swahili = Subject(subjectNames[2], category = categories[0])
        val physics = Subject(subjectNames[3], category = categories[2])
        val chemistry = Subject(subjectNames[4], category = categories[2])
        val biology = Subject(subjectNames[5], category = categories[2])
        val history = Subject(subjectNames[6], category = categories[1])
        val geography = Subject(subjectNames[7], category = categories[1])
        val cre = Subject(subjectNames[8], category = categories[1])
        val ire = Subject(subjectNames[9], category = categories[1])
        val agriculture = Subject(subjectNames[10], category = categories[3])
        val computer = Subject(subjectNames[11], category = categories[3])
        val business = Subject(subjectNames[12], category = categories[3])
        allSubjects.add(math)
        allSubjects.add(english)
        allSubjects.add(swahili)
        allSubjects.add(physics)
        allSubjects.add(chemistry)
        allSubjects.add(biology)
        allSubjects.add(history)
        allSubjects.add(geography)
        allSubjects.add(cre)
        allSubjects.add(ire)
        allSubjects.add(agriculture)
        allSubjects.add(computer)
        allSubjects.add(business)

    }
}