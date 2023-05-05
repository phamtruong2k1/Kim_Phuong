package com.kimphuong.manage.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.kimphuong.manage.R
import com.kimphuong.manage.databinding.FragmentHomeBinding
import com.kimphuong.manage.ui.main.FragmentManageMoney
import com.kimphuong.manage.ui.main.MainActivity
import com.kimphuong.manage.ui.main.TabLayoutAdapter
import com.kimphuong.manage.utils.Constant
import com.kimphuong.manage.utils.getCurrentDaily
import com.kimphuong.manage.utils.getCurrentMonthly
import com.kimphuong.manage.utils.getCurrentYear
import com.kimphuong.manage.utils.setOnSafeClick
import com.kimphuong.manage.utils.showToast

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private lateinit var tabLayoutAdapter : TabLayoutAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayoutAdapter = TabLayoutAdapter(requireActivity().supportFragmentManager)
        setUpViewPager()
        binding.viewPager.adapter = tabLayoutAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tvDate.text = requireContext().getCurrentDaily()
        initListener()
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

    }

    fun initListener() {
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
                            requireContext().getCurrentDaily()
                        }
                        Constant.TAB_WEEKLY -> {
                            "${getCurrentYear()}"
                        }
                        Constant.TAB_MONTHLY -> {
                            requireContext().getCurrentMonthly()
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