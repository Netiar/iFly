package com.example.wingstofly.models

import java.util.*
import kotlin.collections.ArrayList

data class Job(val title: String, val department: String): java.io.Serializable {
    var companyName: String? = null
    var companyImage: Int = 0
    var jobType: String? = null
    var jobPlace: String? = null
    var jobLevel: String? = null
    var description: String = ""
    var requirements = ArrayList<String>()
    var jobAbout = ArrayList<String>()
    var jobBenefits = ArrayList<String>()
    var postDate: Date? = null
    var deadline: Date? = null
    var jobUrl: String? = null
}