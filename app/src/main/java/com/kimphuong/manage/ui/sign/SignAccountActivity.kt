package com.kimphuong.manage.ui.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivityMainBinding
import com.kimphuong.manage.databinding.ActivitySignAccountBinding
import com.kimphuong.manage.ui.main.MainViewModel

class SignAccountActivity :
    BaseActivity<SignAccountViewModel, ActivitySignAccountBinding>(SignAccountViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_sign_account
    }

    override fun initViewModel(viewModel: SignAccountViewModel) {}
}