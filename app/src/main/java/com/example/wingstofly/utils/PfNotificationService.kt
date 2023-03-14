package com.example.wingstofly.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.wingstofly.MainActivity
import com.example.wingstofly.R
import com.example.wingstofly.models.Scholar

class PfNotificationService(private val cont: Context) {
    private val notificationManager = cont.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(scholar: Scholar){

        //creating an intent to open the application
        val intent = Intent(cont, MainActivity::class.java)

        //creating a pending intent instance to initiate the intent
        val pendingIntent = PendingIntent.getActivity(
            cont,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        //creating a notification instance
        val names = scholar.name!!.split(" ")
        val notification = NotificationCompat.Builder(cont, PF_CHANNEL_ID)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("Scholar Number")
            .setContentText("Hello ${names[names.size - 1]} Number: ${scholar.pfNumber}")
            .setContentIntent(pendingIntent).build()

        notificationManager.notify(1, notification)

    }
    companion object{
        const val PF_CHANNEL_ID = "pf_channel"
    }
}