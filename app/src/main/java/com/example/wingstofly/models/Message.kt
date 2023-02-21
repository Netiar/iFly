package com.example.wingstofly.models

class Message : java.io.Serializable{
    var userUid: String? = null
    var description: String? = null

    constructor(){}

    constructor(userId: String, message: String){
        this.userUid = userId
        this.description = message
    }
}