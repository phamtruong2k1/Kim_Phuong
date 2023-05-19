package com.kimphuong.manage.ui.home.weekly

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.base.toDateMonth
import com.kimphuong.manage.base.toMoney
import com.kimphuong.manage.databinding.FragmentHomeWeeklyBinding
import com.kimphuong.manage.db.entity.DataDetail
import com.kimphuong.manage.db.entity.TransactionDetail
import com.kimphuong.manage.ui.main.MainViewModel
import com.kimphuong.manage.utils.Constant
import java.util.*

class HomeWeeklyFragment : BaseFragment<MainViewModel, FragmentHomeWeeklyBinding>(
    MainViewModel::class.java
) {
    val calendar = Calendar.getInstance()
    var listDataAll = mutableListOf<TransactionDetail>()
    var listDataFilter = mutableListOf<TransactionDetail>()
    val listDataWeek = arrayListOf<Pair<Int, List<TransactionDetail>>>()

    val listDataDetailFilter = arrayListOf<Pair<Int, List<DataDetail>>>()

    var weeklyAdapter: MonthWeeklyAdapter? = null
    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeWeeklyBinding {
        return FragmentHomeWeeklyBinding.inflate(inflater, container, false)
    }

    var tabKey: Int = 0
    override fun initView() {
        tabKey = arguments?.getInt(Constant.TAB_KEY) ?: Constant.TAB_DAILY
        weeklyAdapter = MonthWeeklyAdapter(requireContext(), listOf())
        binding.rcvDataWeekly.apply {
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

    private fun filterData() {
        listDataWeek.clear()
        listDataDetailFilter.clear()
        val set = HashSet<Int>()
        listDataFilter.sortWith(compareBy<TransactionDetail> {
            it.year
        }.thenBy { it.month }.thenBy { it.day })
        listDataFilter.forEach {
            set.add(it.month)
        }
        set.forEach { month ->
            val list = listDataFilter.filter { it.month == month }
            listDataWeek.add(Pair(month, list))
        }


        listDataWeek.forEach {
            val listDataOfMonth = getDataOfMonth(it)
            listDataDetailFilter.add(Pair(it.first, listDataOfMonth))
        }

        if (listDataFilter.size != 0) {
            binding.layoutNoData.visibility = View.GONE
            binding.rcvDataWeekly.visibility = View.VISIBLE
            binding.layoutTop.visibility = View.VISIBLE
        } else {
            binding.layoutNoData.visibility = View.VISIBLE
            binding.rcvDataWeekly.visibility = View.GONE
            binding.layoutTop.visibility = View.GONE
        }

        weeklyAdapter?.setData(listDataDetailFilter)
    }

    private fun getDataOfMonth(pair: Pair<Int, List<TransactionDetail>>): List<DataDetail> {
        var listDataDetail = arrayListOf<DataDetail>()
        val calFirstDayOfMonth = getFirstDayOfMonth(pair.first)
        while (calFirstDayOfMonth.get(Calendar.MONTH) < pair.first + 1) {
            val calNextWeek = calFirstDayOfMonth.clone() as Calendar
            calNextWeek.add(Calendar.DAY_OF_MONTH, 7)
            val period = "${calFirstDayOfMonth.toDateMonth()} - ${calNextWeek.toDateMonth()}"
            val sumIncome = pair.second.filter {
                val cal = Calendar.getInstance()
                cal.set(it.year,it.month,it.day)
                it.type && ((cal.timeInMillis >= calFirstDayOfMonth.timeInMillis && cal.timeInMillis <= calNextWeek.timeInMillis))
            }.sumOf { it.amount.toInt() }
            val sumExpend = pair.second.filter {
                val cal = Calendar.getInstance()
                cal.set(it.year,it.month,it.day)
                !it.type && ((cal.timeInMillis >= calFirstDayOfMonth.timeInMillis && cal.timeInMillis <= calNextWeek.timeInMillis))
            }.sumOf { it.amount.toInt() }
            if (sumIncome != 0 || sumExpend != 0) {
                listDataDetail.add(DataDetail(period, sumIncome, sumExpend))
            }
            calFirstDayOfMonth.add(Calendar.DAY_OF_MONTH, 7)
        }
        return listDataDetail
    }

    private fun getFirstDayOfMonth(month: Int): Calendar {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonth = cal.get(Calendar.DAY_OF_WEEK)
        if (firstDayOfMonth > Calendar.MONDAY) {
            cal.add(Calendar.DAY_OF_MONTH, firstDayOfMonth - Calendar.MONDAY)
        }
        return cal
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

    fun updateDataFollowYear(i: Int) {
        calendar.add(Calendar.YEAR, i)
        calculateData()
    }

    fun getCurrentYear() = calendar.get(Calendar.YEAR).toString()
}