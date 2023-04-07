package com.example.wingstofly.api

import kotlinx.coroutines.flow.Flow

interface ConnectivityInterface {
    enum class States{
        Connected ,NotConnected, Loosing, Lost
    }
    fun observe(): Flow<States>

}