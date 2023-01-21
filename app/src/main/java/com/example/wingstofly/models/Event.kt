package com.example.wingstofly.models

import java.util.Date

class Event(var title:String){
    var description:String? = null
    var venue:String? = null
    var date:Date? = null
}

class Job(var title:String){
    var company: String? = null
    var description:String? = null
    var requirements = ArrayList<String>()
    var date:Date? = null
}