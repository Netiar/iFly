package com.example.wingstofly.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Result : Serializable {
    @SerializedName("activity")
    @Expose
    var activity: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("participants")
    @Expose
    var participants: Int? = null

    @SerializedName("price")
    @Expose
    var price: Float? = null

    @SerializedName("link")
    @Expose
    var link: String? = null

    @SerializedName("key")
    @Expose
    var key: String? = null

    @SerializedName("accessibility")
    @Expose
    var accessibility: Float? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param activity
     * @param accessibility
     * @param price
     * @param link
     * @param type
     * @param key
     * @param participants
     */
    constructor(
        activity: String?,
        type: String?,
        participants: Int?,
        price: Float?,
        link: String?,
        key: String?,
        accessibility: Float?
    ) : super() {
        this.activity = activity
        this.type = type
        this.participants = participants
        this.price = price
        this.link = link
        this.key = key
        this.accessibility = accessibility
    }
}