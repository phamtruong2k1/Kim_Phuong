package com.kimphuong.manage.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class ApplicationModule {

    fun provideContext(application: Application): Context = application.applicationContext

    fun provideSharedPreferences(application: Application): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)
}
