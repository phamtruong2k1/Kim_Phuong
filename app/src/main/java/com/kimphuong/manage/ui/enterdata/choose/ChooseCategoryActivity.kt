package com.kimphuong.manage.ui.enterdata.choose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivityChooseAccountBinding
import com.kimphuong.manage.databinding.ActivityChooseCategoryBinding

class ChooseCategoryActivity : BaseActivity<ChooseDataViewModel, ActivityChooseCategoryBinding>(ChooseDataViewModel::class.java) {
    override fun initViewModel(viewModel: ChooseDataViewModel) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityChooseCategoryBinding {
        return ActivityChooseCategoryBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {

    }

}