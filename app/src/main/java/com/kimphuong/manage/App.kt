package com.kimphuong.manage

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.kimphuong.manage.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


private const val LOG_TAG = "MyApplication"

class App : Application() , HasAndroidInjector {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        AndroidThreeTen.init(this)
        instance = this
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}