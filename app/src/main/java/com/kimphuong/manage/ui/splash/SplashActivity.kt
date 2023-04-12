package com.kimphuong.manage.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivitySplashBinding
import com.kimphuong.manage.ui.main.MainActivity
import com.kimphuong.manage.ui.sign.SignAccountActivity
import com.kimphuong.manage.utils.SharePreferenceUtils

class SplashActivity :
    BaseActivity<SplashViewModel, ActivitySplashBinding>(SplashViewModel::class.java) {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (SharePreferenceUtils.getUserLogin(this)) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, SignAccountActivity::class.java))
                finish()
            }
        }, 2000L)
    }

    override fun initData() {

    }

    override fun initListener() {

    }
}