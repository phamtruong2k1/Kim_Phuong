package com.kimphuong.manage

import android.app.Application
import android.content.SharedPreferences
import com.kimphuong.manage.utils.SharePreferenceUtils


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