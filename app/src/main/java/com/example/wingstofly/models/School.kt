package com.example.wingstofly.models

import kotlin.properties.Delegates

class School(private val name:String, private val location: String) {
    private var website:String? = null
    private var studentId:Int? = null

    fun getStudentId() = this.studentId
    fun getWebsite() = this.website
    fun getName() = this.name
    fun getLocation() = this.location

    fun setWebsite(website:String){
        this.website = website
    }

    fun setStudentId(id:Int){
        this.studentId = id
    }
}