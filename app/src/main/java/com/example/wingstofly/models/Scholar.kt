package com.example.wingstofly.models

import com.example.wingstofly.database.Subjects
import com.example.wingstofly.utils.Constants

class Scholar : java.io.Serializable {
    var scholarPfTest: String? = null
    var name: String? = null;
    var status: String? = null
    var pfNumber: String? = null
    var primaryNumber: String? = null
    var otherNumber: String? = null
    var emailAddress: String? = null
    var primarySchool: String? = null
    var primaryMeanGrade: String? = null
    var primaryMeanAGP: Int = 0
    var primaryMarks: Int = 0
    var secondarySchool: String? = null
    var varsity: String? = null
    var meanGrade = ArrayList<String>()
    var meanScore = ArrayList<Int>()
    var meanAGP = ArrayList<Int>()
    var coursesName: String? = null
    var varsityGrade: String? = null
    var origin: String? = null
    var tertiaryInstitutions = ArrayList<School>()
    var password: String? = null
    private val agpValues = HashMap<String, Int>()
    var skillSet: String? = null
    var leadershipRoles = ArrayList<Event>()
    var workPlaces = ArrayList<Job>()
    var formGrades = HashMap<String, ArrayList<Subject>>()
    var form: Int = 4

    constructor() {}
    constructor(name: String, status: String) {
        this.name = name
        this.status = status


    }

    private fun assignGrades(marks: Int): String {

        agpValues["A"] = 12
        agpValues["A-"] = 11
        agpValues["B+"] = 10
        agpValues["B"] = 9
        agpValues["B-"] = 8
        agpValues["C+"] = 7
        agpValues["C"] = 6
        agpValues["C-"] = 5
        agpValues["D+"] = 4
        agpValues["D"] = 3
        agpValues["D-"] = 2
        agpValues["E"] = 1

        if (marks >= 79) {
            return "A"
        } else if (marks in 75..78) {
            return "A-"
        } else if (marks in 70..74) {
            return "B+"
        } else if (marks in 65..69) {
            return "B"
        } else if (marks in 60..64) {
            return "B-"
        } else if (marks in 55..59) {
            return "C+"
        } else if (marks in 45..54) {
            return "C"
        } else if (marks in 40..44) {
            return "C-"
        } else if (marks in 35..39) {
            return "D+"
        } else if (marks in 30..34) {
            return "D"
        }else{
            return "E"
        }
    }

    val list = ArrayList<Subject>()
    fun addSubject(subject: Subject) {
        subject.grade = assignGrades(subject.score)
        subject.agp = assignAGP(subject.grade!!)!!
        list.add(subject)
        for (i in 0 until form) {
            formGrades[i.toString()] = list
        }
        calculateMeanScore()
    }

    private fun assignAGP(grade: String): Int? {
        for (key in agpValues.keys) {
            if (key == grade) {
                return agpValues[key]
            }
        }
        return 0
    }

    private fun calculateMeanScore() {
        var formScores = HashMap<Int, Int>()

        for (i in 0 until formGrades.size) {
            var totalMarks = 0
            for (j in formGrades[i.toString()]!!.indices) {
                totalMarks += formGrades[i.toString()]!![j].score
            }
            formScores[i] = totalMarks
        }

        for (i in 0 until form) {
            val mean = formScores[i]!! / formGrades[i.toString()]!!.size
            meanScore.add(mean)
            meanGrade.add(assignGrades(mean))
            meanAGP.add(assignAGP(meanGrade[i]!!)!!)
        }
    }

    fun calculateDeviation(agp: Int): Int {
        var deviation = 0
        deviation = Constants.CUT_POINT_OFF_POINTS - agp
        return deviation
    }


}

class ScholarStatus {
    var highSchool: String = "high-school"
    var campus: String = "University scholar"
    var preCampus: String = "A post graduate"
}

class Subject : java.io.Serializable {
    var name: String? = null;
    var category: String? = null
    var grade: String? = null;
    var agp: Int = 0
    var score: Int = 0

    constructor() {}
    constructor(name: String, category: String) {
        this.name = name
        this.category = category
    }
}