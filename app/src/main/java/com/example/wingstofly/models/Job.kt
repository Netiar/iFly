package models

import java.util.*
import kotlin.collections.ArrayList

data class Job(val title: String, val department: String) {
    var companyName: String? = null
    var companyImage: String? = null
    var jobType: String? = null
    var jobPlace: String? = null
    var jobLevel: String? = null
    var description: String? = null
    var requirements = ArrayList<String>()
    var jobAbout = ArrayList<String>()
    var jobBenefits = ArrayList<String>()
    var postDate: Date? = null
    var deadline: Date? = null
    var jobUrl: String? = null
}