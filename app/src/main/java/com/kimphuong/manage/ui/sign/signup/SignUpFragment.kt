package com.kimphuong.manage.ui.sign.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.databinding.FragmentSignUpBinding


class SignUpFragment : BaseFragment<SignUpViewModel, FragmentSignUpBinding>(SignUpViewModel::class.java) {
    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {

    }
}