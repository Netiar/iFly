package com.example.wingstofly.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ResultOpenDb : Serializable {
    @SerializedName("response_code")
    @Expose
    var responseCode: Int? = null

    @SerializedName("results")
    @Expose
    private var results: List<Result__1>? = null

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param results
     * @param responseCode
     */
    constructor(responseCode: Int?, results: List<Result__1>?) : super() {
        this.responseCode = responseCode
        this.results = results
    }

    fun getResults(): List<Result__1>? {
        return results
    }

    fun setResults(results: List<Result__1>?) {
        this.results = results
    }
}