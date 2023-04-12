package com.kimphuong.manage.di.module


import com.kimphuong.manage.ui.main.MainActivity
import com.kimphuong.manage.ui.sign.SignAccountActivity
import com.kimphuong.manage.ui.splash.SplashActivity

abstract class ActivityModule {
    internal abstract fun mainActivity(): MainActivity
    internal abstract fun splashActivity(): SplashActivity
    internal abstract fun signAccountActivity(): SignAccountActivity

}
