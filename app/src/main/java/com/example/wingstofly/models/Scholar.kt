package com.example.wingstofly.models


class Scholar(private val name:String, private val status:String ) {
    private lateinit var origin:String
    private var id:Int? = 0

    fun getId() = this.id
    fun getOrigin() = this.origin

    fun setOrigin(origin: String){
        this.origin = origin
    }

    fun setId(id:Int){
        this.id = id
    }

}