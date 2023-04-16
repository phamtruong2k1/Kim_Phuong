package com.kimphuong.manage.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.tabs.TabLayout
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivityMainBinding
import com.kimphuong.manage.utils.*

class MainActivity :
    BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class.java) {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private val tabLayoutAdapter by lazy {
        TabLayoutAdapter(supportFragmentManager)
    }

    private fun setUpViewPager() {
        /**add fragment to view pager*/
        tabLayoutAdapter.addTitle(
            mutableListOf(
                getString(R.string.tab_daily),
                getString(R.string.tab_weekly),
                getString(R.string.tab_monthly),
                getString(R.string.tab_calendar)
            )
        )
        tabLayoutAdapter.addFragment(
            mutableListOf(
                FragmentManageMoney().apply {
                    val bundle = Bundle()
                    bundle.putInt(Constant.TAB_KEY, Constant.TAB_DAILY)
                    arguments = bundle
                },
                FragmentManageMoney().apply {
                    val bundle = Bundle()
                    bundle.putInt(Constant.TAB_KEY, Constant.TAB_WEEKLY)
                    arguments = bundle
                },
                FragmentManageMoney().apply {
                    val bundle = Bundle()
                    bundle.putInt(Constant.TAB_KEY, Constant.TAB_MONTHLY)
                    arguments = bundle
                },
                FragmentManageMoney().apply {
                    val bundle = Bundle()
                    bundle.putInt(Constant.TAB_KEY, Constant.TAB_CALENDAR)
                    arguments = bundle
                }
            )
        )
        binding.viewPager.adapter = tabLayoutAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun initView() {
        setUpViewPager()
    }

    override fun initData() {
        binding.tvDate.text = getCurrentDaily()
    }

    override fun initListener() {
        with(binding) {
            btnBackDate.setOnSafeClick {
                showToast("back")
            }
            btnNextDate.setOnSafeClick {
                showToast("next")
            }
            btnSearch.setOnSafeClick {
                showToast("search")
            }
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    binding.tvDate.text = when (tab?.position?.plus(1)) {
                        Constant.TAB_DAILY -> {
                            getCurrentDaily()
                        }
                        Constant.TAB_WEEKLY -> {
                            "${getCurrentYear()}"
                        }
                        Constant.TAB_MONTHLY -> {
                            getCurrentMonthly()
                        }
                        Constant.TAB_CALENDAR -> {
                            "${getCurrentYear()}"
                        }
                        else -> {
                            "${getCurrentYear()}"
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
        }
    }
}