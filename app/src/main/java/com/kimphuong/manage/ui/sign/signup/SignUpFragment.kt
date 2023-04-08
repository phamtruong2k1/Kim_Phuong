package com.kimphuong.manage.ui.sign.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.databinding.FragmentSignAccountBinding
import com.kimphuong.manage.databinding.FragmentSignUpBinding
import com.kimphuong.manage.ui.sign.SignAccountViewModel


class SignUpFragment : BaseFragment<SignUpViewModel, FragmentSignUpBinding>(
    R.layout.fragment_sign_up,
    SignUpViewModel::class.java
){
    override fun init() {
        super.init()

    }
}