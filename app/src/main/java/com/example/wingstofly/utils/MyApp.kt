package com.example.wingstofly.utils

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        //check the android version of the user
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //creating a notification channel instance
            val channel = NotificationChannel(
                PfNotificationService.PF_CHANNEL_ID,
                "Scholar News",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Get scholar news information"

            //initialising an instance of the notification manager to create the channel.
             val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
             notificationManager.createNotificationChannel(channel)


        }
    }
}