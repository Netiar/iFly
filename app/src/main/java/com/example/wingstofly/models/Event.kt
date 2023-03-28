package com.example.wingstofly.models

import java.util.Date

class Event: java.io.Serializable{
    var title:String? = null
    var id: Int = 0
    var description:String? = null
    var venue:String? = null
    var date:Date? = null
    var eventOwner: String? = null

    constructor(){}
    constructor(title:String){
        this.title = title
    }
}

