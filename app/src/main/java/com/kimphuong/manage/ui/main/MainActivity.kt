package com.kimphuong.manage.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.google.android.material.tabs.TabLayout
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivityMainBinding
import com.kimphuong.manage.ui.enterdata.EnterDataActivity
import com.kimphuong.manage.utils.*

class MainActivity :
    BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class.java) {
    companion object {
        const val TAB_HOME = 1
        const val TAB_STATISTIC = 2
        const val TAB_ACCOUNT = 3
        const val TAB_MORE = 4
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        val adapter = TabViewAdapter(this@MainActivity, supportFragmentManager)
        binding.viewPager.adapter = adapter
        handleClickBottom(TAB_HOME)
    }

    override fun initData() {

    }

    override fun initListener() {
        with(binding) {
            btnAdd.setOnSafeClick {
                openActivity(EnterDataActivity::class.java,
                    bundleOf("action" to "add")
                )
            }
            //click home
            layoutBtnHome.setOnSafeClick { handleClickBottom(TAB_HOME) }
            imgHome.setOnSafeClick { handleClickBottom(TAB_HOME) }
            txtHome.setOnSafeClick { handleClickBottom(TAB_HOME) }
            //click statistic
            layoutBtnStatistic.setOnSafeClick { handleClickBottom(TAB_STATISTIC) }
            imgStatistic.setOnSafeClick { handleClickBottom(TAB_STATISTIC) }
            txtStatistic.setOnSafeClick { handleClickBottom(TAB_STATISTIC) }
            //click account
            layoutBtnAccount.setOnSafeClick { handleClickBottom(TAB_ACCOUNT) }
            imgAccount.setOnSafeClick { handleClickBottom(TAB_ACCOUNT) }
            txtAccount.setOnSafeClick { handleClickBottom(TAB_ACCOUNT) }
            //click more
            layoutBtnMore.setOnSafeClick { handleClickBottom(TAB_MORE) }
            imgMore.setOnSafeClick { handleClickBottom(TAB_MORE) }
            txtMore.setOnSafeClick { handleClickBottom(TAB_MORE) }
        }
    }

    private fun resetColorTextView(vararg listTextView: TextView) {
        listTextView.forEach {
            it.resetColorText()
        }
    }

    private var tabSelected = TAB_HOME
    private fun handleClickBottom(tabSelect: Int) {
        binding.viewPager.currentItem = tabSelect
        with(binding) {
            when (tabSelect) {
                TAB_HOME -> {
                    txtHome.setColorTextSelect()
                    resetColorTextView(txtStatistic, txtAccount, txtMore)
                    imgHome.setImageResource(R.drawable.ic_home_selected)
                    imgStatistic.setImageResource(R.drawable.ic_statistic)
                    imgAccount.setImageResource(R.drawable.ic_account)
                    imgMore.setImageResource(R.drawable.ic_more)
                }
                TAB_STATISTIC -> {
                    txtStatistic.setColorTextSelect()
                    resetColorTextView(txtHome, txtAccount, txtMore)
                    imgHome.setImageResource(R.drawable.ic_home)
                    imgStatistic.setImageResource(R.drawable.ic_statistic_selected)
                    imgAccount.setImageResource(R.drawable.ic_account)
                    imgMore.setImageResource(R.drawable.ic_more)
                }
                TAB_ACCOUNT -> {
                    txtAccount.setColorTextSelect()
                    resetColorTextView(txtHome, txtStatistic, txtMore)
                    imgHome.setImageResource(R.drawable.ic_home)
                    imgStatistic.setImageResource(R.drawable.ic_statistic)
                    imgAccount.setImageResource(R.drawable.ic_account_selected)
                    imgMore.setImageResource(R.drawable.ic_more)
                }
                TAB_MORE -> {
                    txtMore.setColorTextSelect()
                    resetColorTextView(txtHome, txtStatistic, txtAccount)
                    imgHome.setImageResource(R.drawable.ic_home)
                    imgStatistic.setImageResource(R.drawable.ic_statistic)
                    imgAccount.setImageResource(R.drawable.ic_account)
                    imgMore.setImageResource(R.drawable.ic_more_selected)
                }
            }
        }
    }

    private fun TextView.resetColorText() {
        this.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.gray3))
    }

    private fun TextView.setColorTextSelect() {
        this.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.color_app))
    }
}