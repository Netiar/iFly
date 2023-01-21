package com.example.wingstofly.models


class Scholar(private val name:String, private val status:String ) {
    private lateinit var origin:String
    private var id:Int? = 0
    var primarySchool: String? = null
    var secondarySchool: String? = null
    var varsity: String? = null

    fun getId() = this.id
    fun getName() = this.name
    fun getStatus() = this.status
    fun getOrigin() = this.origin

    fun setOrigin(origin: String){
        this.origin = origin
    }

    fun setId(id:Int){
        this.id = id
    }

}