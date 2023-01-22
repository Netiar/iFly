package com.example.wingstofly.models


class Scholar(private val name:String, private val status:String ) {
    private lateinit var origin:String
    var pfNumber: String = ""
    var primarySchool: String? = null
    var secondarySchool: String? = null
    var varsity: String? = null

    fun getName() = this.name
    fun getStatus() = this.status
    fun getOrigin() = this.origin

    fun setOrigin(origin: String){
        this.origin = origin
    }

    fun addSubject(subject: Subject){
        subject.grade = assignGrades(subject.score)
        subject.agp = assignAGP(subject.grade!!)!!
        currentSubjects.add(subject)
        calculateMeanScore()
    }
    private fun assignGrades(marks: Int): String{

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

        if(marks >= 79){
            return "A"
        }else if (marks in 75..78){
            return "A-"
        }else if (marks in 70..74){
            return "B+"
        }else if (marks in 65..69){
            return "B"
        }else if (marks in 60..64){
            return "B-"
        }else if (marks in 55..59){
            return "C+"
        }else if (marks in 45..54){
            return "C"
        }else if (marks in 40..44){
            return "C-"
        }else if (marks in 35..39){
            return "D+"
        }else if (marks in 30..34){
            return "D"
        }else if (marks in 25..29){
            return "C+"
        }
        return "E"
    }

}

data class Subject(var name: String, var category: String){
    var grade: String? = null;
    var agp: Int = 0
    var score:Int = 0
}