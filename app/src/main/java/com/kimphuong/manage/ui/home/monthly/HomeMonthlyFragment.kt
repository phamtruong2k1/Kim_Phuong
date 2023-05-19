package com.kimphuong.manage.ui.home.monthly

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.base.getStringMonth
import com.kimphuong.manage.base.toMoney
import com.kimphuong.manage.databinding.FragmentHomeMonthlyBinding
import com.kimphuong.manage.db.entity.DataDetail
import com.kimphuong.manage.db.entity.TransactionDetail
import com.kimphuong.manage.ui.home.weekly.MonthWeeklyAdapter
import com.kimphuong.manage.ui.main.MainViewModel
import com.kimphuong.manage.utils.Constant
import java.util.*


class HomeMonthlyFragment : BaseFragment<MainViewModel, FragmentHomeMonthlyBinding>(
    MainViewModel::class.java
) {

    val calendar = Calendar.getInstance()
    var listDataAll = mutableListOf<TransactionDetail>()
    var listDataFilter = mutableListOf<TransactionDetail>()
    val listDataYear = arrayListOf<Pair<Int, List<TransactionDetail>>>()

    val listDataYearFilter = arrayListOf<Pair<Int, List<DataDetail>>>()

    var weeklyAdapter: MonthWeeklyAdapter? = null

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeMonthlyBinding {
        return FragmentHomeMonthlyBinding.inflate(inflater, container, false)
    }

    var tabKey: Int = 0
    override fun initView() {
        tabKey = arguments?.getInt(Constant.TAB_KEY) ?: Constant.TAB_DAILY

        weeklyAdapter = MonthWeeklyAdapter(requireContext(), listOf())
        binding.rcvMonth.apply {
            adapter = weeklyAdapter
            setHasFixedSize(true)
        }
    }

    override fun initData() {
    }

    override fun initListener() {
        viewModel?.getAllTransactionDetail()?.observe(this) {
            listDataAll = it
            calculateData()
        }
    }

    private fun calculateData() {
        val year = calendar.get(Calendar.YEAR)
        listDataFilter =
            listDataAll.filter { it.year == year }.toMutableList()

        val sumIncome = listDataFilter.filter { it.type }.sumOf { it.amount.toInt() }
        val sumExpend = listDataFilter.filter { !it.type }.sumOf { it.amount.toInt() }
        binding.tvIncome.text = sumIncome.toMoney()
        binding.tvExpense.text = sumExpend.toMoney()
        binding.tvTotal.text = (sumIncome - sumExpend).toMoney()
        filterData()
    }


    private fun filterData() {
        listDataYear.clear()
        listDataYearFilter.clear()
        val set = HashSet<Int>()
        listDataFilter.sortWith(compareBy<TransactionDetail> {
            it.year
        }.thenBy { it.month }.thenBy { it.day })
        listDataFilter.forEach {
            set.add(it.year)
        }
        set.forEach { year ->
            val list = listDataFilter.filter { it.year == year }
            listDataYear.add(Pair(year, list))
        }


        listDataYear.forEach {
            val listDataOfMonth = getDataOfYear(it)
            listDataYearFilter.add(Pair(it.first, listDataOfMonth))
        }

        if (listDataFilter.size != 0) {
            binding.layoutNoData.visibility = View.GONE
            binding.rcvMonth.visibility = View.VISIBLE
            binding.layoutTop.visibility = View.VISIBLE
        } else {
            binding.layoutNoData.visibility = View.VISIBLE
            binding.rcvMonth.visibility = View.GONE
            binding.layoutTop.visibility = View.GONE
        }

        weeklyAdapter?.setData(listDataYearFilter)
    }


    private fun getDataOfYear(pair: Pair<Int, List<TransactionDetail>>): List<DataDetail> {
        var listDataMonth = arrayListOf<DataDetail>()
        repeat(12) { time ->
            val period = "${requireContext().getStringMonth(time)}"
            val sumIncome = pair.second.filter {
                it.type && it.month == time
            }.sumOf { it.amount.toInt() }
            val sumExpend = pair.second.filter {
                !it.type && it.month == time
            }.sumOf { it.amount.toInt() }
            if (sumIncome != 0 || sumExpend != 0) {
                listDataMonth.add(DataDetail(period, sumIncome, sumExpend))
            }
        }
        return listDataMonth
    }

    fun updateDataFollowYear(i: Int) {
        calendar.add(Calendar.YEAR, i)
        calculateData()
    }

    fun getCurrentYear() = "${calendar.get(Calendar.YEAR)}"
}