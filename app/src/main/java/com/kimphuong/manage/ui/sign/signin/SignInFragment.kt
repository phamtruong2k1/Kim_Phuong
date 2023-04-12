package com.kimphuong.manage.ui.sign.signin

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment<SignInViewModel, FragmentSignInBinding>(
    SignInViewModel::class.java
) {
    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(inflater, container, false)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {

    }
}