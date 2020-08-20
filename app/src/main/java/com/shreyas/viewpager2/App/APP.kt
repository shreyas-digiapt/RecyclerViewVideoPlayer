package com.shreyas.viewpager2.App

import android.app.Application

class APP :Application() {

    companion object {
        private lateinit var instance:APP

        @Synchronized
        fun getInstance():APP{
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

 ///////////////////////////////////////////////////////////////////////////////////////////////////
    var qwerty:String? = null
}