package com.example.loginapp

import android.app.Application
import android.content.Context
import java.io.IOException
import java.util.Properties

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        loadEnvVariables()
    }

    private fun loadEnvVariables() {
        val properties = Properties()
        try {
            val inputStream = assets.open(".env")
            properties.load(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        for (key in properties.stringPropertyNames()) {
            val value = properties.getProperty(key)
            System.setProperty(key, value)
        }
    }
}