package com.example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var progressBar: ProgressBar
//если надо чтобы ресивер обрашался к вью элиментам то создаем его через анонимный класс
    private val receiver = object:BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (p1?.action == "loader"){
                val percent = p1.getIntExtra("percent",0)
                //принимаем интенд с сервиса
                progressBar.progress = percent
            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)

        val intendFilter = IntentFilter().apply {
        //добавляем свой акшен который в сервисах указали
            addAction("loader")
        }
        //регистрируем и запускаем ресивер с нашими интенд фильтрами
        registerReceiver(receiver,intendFilter)
        //запускаем сервис на выполнение
        Intent(this,MyService::class.java).apply {
            startService(this)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        // отписываемся от ресивира чтобы не было утечек памяти
        unregisterReceiver(receiver)
    }
}