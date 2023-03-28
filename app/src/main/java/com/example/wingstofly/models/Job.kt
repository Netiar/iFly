package com.example.wingstofly.models

import java.util.*
import kotlin.collections.ArrayList

class Job: java.io.Serializable {
    var title: String? = null
    var department: String? = null
    var companyName: String? = null
    var companyImage: Int = 0
    var jobType: String? = null
    var jobPlace: String? = null
    var jobLevel: String? = null
    var description: String = ""
    var requirements = ArrayList<String>()
    var jobAbout = ArrayList<String>()
    var jobBenefits = ArrayList<String>()
    var postDate: String? = null
    var deadline: String? = null
    var jobUrl: String? = null
    var skillSet: String? = null

    constructor(){}

    constructor(name: String, dept: String){
        this.title = name
        this.department = dept
    }
}