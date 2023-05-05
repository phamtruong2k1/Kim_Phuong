package com.kimphuong.manage.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kimphuong.manage.ui.account.AccountFragment
import com.kimphuong.manage.ui.home.view.HomeFragment
import com.kimphuong.manage.ui.more.MoreFragment
import com.kimphuong.manage.ui.statistic.StatisticFragment

class TabViewAdapter (private val myContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> StatisticFragment()
            2 -> AccountFragment()
            3 -> MoreFragment()
            else -> HomeFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }
}