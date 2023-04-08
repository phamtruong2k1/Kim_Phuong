package com.kimphuong.manage.ui.sign.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment<SignInViewModel, FragmentSignInBinding>(
    R.layout.fragment_sign_in,
    SignInViewModel::class.java
) {
    override fun init() {
        super.init()

    }
}