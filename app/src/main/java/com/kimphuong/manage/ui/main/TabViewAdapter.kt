package com.kimphuong.manage.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kimphuong.manage.ui.account.AccountFragment
import com.kimphuong.manage.ui.home.view.HomeFragment
import com.kimphuong.manage.ui.more.MoreFragment
import com.kimphuong.manage.ui.statistic.StatisticFragment

class TabViewAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

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