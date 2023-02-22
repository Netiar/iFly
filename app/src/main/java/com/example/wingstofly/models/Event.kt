package com.example.wingstofly.models

import java.util.Date

class Event(var title:String): java.io.Serializable{
    var id: Int = 0
    var description:String? = null
    var venue:String? = null
    var date:Date? = null
    var eventOwner: String? = null
}

