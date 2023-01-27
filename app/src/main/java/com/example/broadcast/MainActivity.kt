package com.example.broadcast

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
   //инициализируем класс ресивера
    private val receiver = MyReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //делаем интендФильтры с нужными нам полями
        val intendFilter = IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
        }
        //регистрируем и запускаем ресивер с нашими интенд фильтрами
        registerReceiver(receiver,intendFilter)

    }
    //начиная с версии api 26 у ресиверов зарегистрированных в манифесте не все акшины работают
    //если мы регистрируем их здесь то в манифест ничего писать не надо


    override fun onDestroy() {
        super.onDestroy()
        // отписываемся от ресивира чтобы не было утечек памяти
        unregisterReceiver(receiver)
    }
}