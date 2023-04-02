package com.example.wingstofly.models

import java.util.*
import kotlin.properties.Delegates

class School: java.io.Serializable {
    var name:String?  = null
    var course:String? = null
    var studentId:Int? = null
    var startYear: String? = null
    var endYear: String? = null
    var schoolType: String? = null

    constructor(name:String){
        this.name = name
    }
    constructor(){}
}