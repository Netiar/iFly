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

}