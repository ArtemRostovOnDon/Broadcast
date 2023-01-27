package com.example.broadcast

import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlin.concurrent.thread

class MyService: android.app.Service() {
    private val localBroadcastReceiver by lazy {
        LocalBroadcastManager.getInstance(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread {
            for (i in 0..10){
                Thread.sleep(1000)
                Intent("loader").apply {
                    putExtra("percent",i*10)
                    //ложим наш интенд в ресивер
                    localBroadcastReceiver.sendBroadcast(this)
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}