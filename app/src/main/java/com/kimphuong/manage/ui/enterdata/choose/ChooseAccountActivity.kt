package com.kimphuong.manage.ui.enterdata.choose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivityChooseAccountBinding

class ChooseAccountActivity : BaseActivity<ChooseDataViewModel, ActivityChooseAccountBinding>(ChooseDataViewModel::class.java) {

    override fun initViewModel(viewModel: ChooseDataViewModel) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityChooseAccountBinding {
        return ActivityChooseAccountBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {

    }
}