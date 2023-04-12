package com.kimphuong.manage

import android.app.Application


private const val LOG_TAG = "MyApplication"

class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}