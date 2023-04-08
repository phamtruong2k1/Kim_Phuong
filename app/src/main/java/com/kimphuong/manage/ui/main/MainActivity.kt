package com.kimphuong.manage.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivityMainBinding

class MainActivity :
    BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initViewModel(viewModel: MainViewModel) {}
}