package com.kimphuong.manage

import android.app.Application
import android.content.SharedPreferences
import com.kimphuong.manage.di.AppInjector
import com.kimphuong.manage.utils.SharePreferenceUtils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


private const val LOG_TAG = "MyApplication"

class MyApplication : Application(), HasAndroidInjector {

    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppInjector.init(this)

    }
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}