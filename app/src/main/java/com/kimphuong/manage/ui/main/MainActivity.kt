package com.kimphuong.manage.ui.main

import android.view.LayoutInflater
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivityMainBinding

class MainActivity :
    BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class.java) {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {

    }
}