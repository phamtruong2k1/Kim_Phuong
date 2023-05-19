package com.kimphuong.manage.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.kimphuong.manage.R
import com.kimphuong.manage.databinding.FragmentHomeBinding
import com.kimphuong.manage.ui.home.calender.HomeCalenderFragment
import com.kimphuong.manage.ui.home.daily.HomeDailyFragment
import com.kimphuong.manage.ui.home.monthly.HomeMonthlyFragment
import com.kimphuong.manage.ui.home.weekly.HomeWeeklyFragment
import com.kimphuong.manage.ui.main.TabLayoutAdapter
import com.kimphuong.manage.ui.search.SearchActivity
import com.kimphuong.manage.utils.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var tabLayoutAdapter: TabLayoutAdapter

    private var currentPage = 0

    private var index = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayoutAdapter = TabLayoutAdapter(childFragmentManager)
        setUpViewPager()
        binding.viewPager.adapter = tabLayoutAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tvDate.text = requireContext().getCurrentDaily(index)
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
                HomeDailyFragment().apply {
                    val bundle = Bundle()
                    bundle.putInt(Constant.TAB_KEY, Constant.TAB_DAILY)
                    arguments = bundle
                },
                HomeWeeklyFragment().apply {
                    val bundle = Bundle()
                    bundle.putInt(Constant.TAB_KEY, Constant.TAB_WEEKLY)
                    arguments = bundle
                },
                HomeMonthlyFragment().apply {
                    val bundle = Bundle()
                    bundle.putInt(Constant.TAB_KEY, Constant.TAB_MONTHLY)
                    arguments = bundle
                },
                HomeCalenderFragment().apply {
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
                handleOnClickBack()
            }
            btnNextDate.setOnSafeClick {
                handleOnCLickNext()
            }
            btnSearch.setOnSafeClick {
                startActivity(Intent(requireContext(),SearchActivity::class.java))
            }
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    binding.tvDate.text = when (tab?.position) {
                        Constant.TAB_DAILY -> {
                            requireContext().getCurrentDaily(index)
                        }
                        Constant.TAB_WEEKLY -> {
                            (tabLayoutAdapter.getItem(1) as HomeWeeklyFragment).getCurrentYear()
                        }
                        Constant.TAB_MONTHLY -> {
                            (tabLayoutAdapter.getItem(2) as HomeMonthlyFragment).getCurrentYear()
                        }
                        Constant.TAB_CALENDAR -> {
                            (tabLayoutAdapter.getItem(3) as HomeCalenderFragment).getCurrentMonth(requireContext())
                        }
                        else -> {
                            "${getCurrentYear()}"
                        }
                    }
                    currentPage = tab?.position ?: 0
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
        }
    }

    private fun handleOnCLickNext() {
        when (currentPage) {
            Constant.TAB_DAILY -> {
                index += 1
                (tabLayoutAdapter.getItem(currentPage) as HomeDailyFragment).updateDataFollowDay(
                    1
                )
                binding.tvDate.text = requireContext().getCurrentDaily(index)
            }
            Constant.TAB_WEEKLY -> {
                (tabLayoutAdapter.getItem(currentPage) as HomeWeeklyFragment).updateDataFollowYear(
                    1
                )
                binding.tvDate.text = (tabLayoutAdapter.getItem(currentPage) as HomeWeeklyFragment).getCurrentYear()
            }

            Constant.TAB_MONTHLY -> {
                (tabLayoutAdapter.getItem(currentPage) as HomeMonthlyFragment).updateDataFollowYear(
                    1
                )

                binding.tvDate.text = (tabLayoutAdapter.getItem(currentPage) as HomeMonthlyFragment).getCurrentYear()
            }

            Constant.TAB_CALENDAR -> {
                (tabLayoutAdapter.getItem(currentPage) as HomeCalenderFragment).updateDataFollowMonth(
                    1
                )

                binding.tvDate.text = (tabLayoutAdapter.getItem(currentPage) as HomeCalenderFragment).getCurrentMonth(requireContext())
            }
        }
    }

    private fun handleOnClickBack() {
        when (currentPage) {
            Constant.TAB_DAILY -> {
                index -= 1
                (tabLayoutAdapter.getItem(currentPage) as HomeDailyFragment).updateDataFollowDay(
                    -1
                )
                binding.tvDate.text = requireContext().getCurrentDaily(index)
            }
            Constant.TAB_WEEKLY -> {
                (tabLayoutAdapter.getItem(currentPage) as HomeWeeklyFragment).updateDataFollowYear(
                    -1
                )
                binding.tvDate.text = (tabLayoutAdapter.getItem(currentPage) as HomeWeeklyFragment).getCurrentYear()
            }

            Constant.TAB_MONTHLY -> {
                (tabLayoutAdapter.getItem(currentPage) as HomeMonthlyFragment).updateDataFollowYear(-1)

                binding.tvDate.text = (tabLayoutAdapter.getItem(currentPage) as HomeMonthlyFragment).getCurrentYear()
            }

            Constant.TAB_CALENDAR -> {
                (tabLayoutAdapter.getItem(currentPage) as HomeCalenderFragment).updateDataFollowMonth(-1)

                binding.tvDate.text = (tabLayoutAdapter.getItem(currentPage) as HomeCalenderFragment).getCurrentMonth(requireContext())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //reload data
    }

    override fun onPause() {
        super.onPause()
        Log.d("abcc", "onPause: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("abcc", "onDestroyView: ")
    }
}