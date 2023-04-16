package com.kimphuong.manage.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.databinding.FragmentManageMoneyBinding
import com.kimphuong.manage.utils.Constant
import com.kimphuong.manage.utils.getCurrentToday

class FragmentManageMoney : BaseFragment<MainViewModel, FragmentManageMoneyBinding>(
    MainViewModel::class.java
) {
    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): FragmentManageMoneyBinding {
        return FragmentManageMoneyBinding.inflate(inflater, container, false)
    }

    var tabKey: Int = 0
    override fun initView() {
        tabKey = arguments?.getInt(Constant.TAB_KEY) ?: Constant.TAB_DAILY
    }

    override fun initData() {
        with(binding) {
            tvIncome.text = "15.000.000"
            tvExpense.text = "2.000.000"
            tvTotal.text = "13.000.000"
            tvToDay.text = requireContext().getCurrentToday()
            tvExpenseToDay.text = "15.000.000"
            tvTotalToDay.text = "1.500.000"
        }
    }

    override fun initListener() {
    }
}