package com.kimphuong.manage.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.databinding.FragmentAccountBinding
import com.kimphuong.manage.utils.openActivity

class AccountFragment : BaseFragment<AccountViewModel, FragmentAccountBinding>(AccountViewModel::class.java) {
    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAccountBinding {
        return FragmentAccountBinding.inflate(inflater, container, false)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        binding.imgAdd.setOnClickListener {
            requireContext().openActivity(AddAccountActivity::class.java)
        }
    }


}