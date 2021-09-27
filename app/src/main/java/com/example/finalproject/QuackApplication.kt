package com.example.finalproject

import android.app.Application
import com.example.finalproject.model.local.QuackDao
import com.example.finalproject.model.local.QuackDatabase

class QuackApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        quackDao = QuackDatabase.newInstance(this).getDao()
    }


    companion object{
        lateinit var quackDao: QuackDao
    }
}