package com.example.wingstofly.models

import java.util.*
import kotlin.properties.Delegates

class School(val name:String, val location: String) {
    var website:String? = null
    var studentId:Int? = null
    var startYear: Date? = null
    var endYear: Date? = null
    var schoolType: String? = null

}