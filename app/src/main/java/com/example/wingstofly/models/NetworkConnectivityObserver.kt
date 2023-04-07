package com.example.wingstofly.models

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.example.wingstofly.api.ConnectivityInterface
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkConnectivityObserver(private val context: Context) : ConnectivityInterface {
    private val contentManager =
        context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<ConnectivityInterface.States> {
        return callbackFlow {
            val networkInfo = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch {
                        send(ConnectivityInterface.States.Connected)
                    }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch {
                        send(ConnectivityInterface.States.Loosing)
                    }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch {
                        send(ConnectivityInterface.States.Lost)
                    }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch {
                        send(ConnectivityInterface.States.NotConnected)
                    }
                }
            }
            contentManager.registerDefaultNetworkCallback(networkInfo)
            awaitClose {
                contentManager.unregisterNetworkCallback(networkInfo)
            }
        }.distinctUntilChanged()
    }

}