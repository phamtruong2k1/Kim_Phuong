package com.kimphuong.manage.ui.sign

import android.view.LayoutInflater
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivitySignAccountBinding

class SignAccountActivity :
    BaseActivity<SignAccountViewModel, ActivitySignAccountBinding>(SignAccountViewModel::class.java) {

    override fun inflateViewBinding(inflater: LayoutInflater): ActivitySignAccountBinding {
        return ActivitySignAccountBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {

    }
}