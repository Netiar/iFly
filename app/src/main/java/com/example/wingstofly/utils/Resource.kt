package com.example.wingstofly.utils

sealed class Resource<T>(val newsData: T? = null, val questMessage:String? = null) {
    class Success<T>(private val data: T): Resource<T>(data)
    class Failure<T>(private val data: T? = null, private val message: String): Resource<T>(data, message)
    class Loading<T>: Resource<T>()
}