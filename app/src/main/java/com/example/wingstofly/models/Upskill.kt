package com.example.wingstofly.models

class Upskill: java.io.Serializable {
    var title:String? = null
    var description:String? = null
    var type:String? = null
    var uri: String? = null

    constructor(title:String){
        this.title = title
    }
    constructor()
}