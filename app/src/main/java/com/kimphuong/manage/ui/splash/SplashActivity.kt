package com.kimphuong.manage.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivitySplashBinding
import com.kimphuong.manage.ui.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity :
    BaseActivity<SplashViewModel, ActivitySplashBinding>(SplashViewModel::class.java) {
    companion object {
        const val TIME_COUNT = 100L
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, TIME_COUNT)
    }

    override fun initData() {

    }

    override fun initListener() {

    }
}