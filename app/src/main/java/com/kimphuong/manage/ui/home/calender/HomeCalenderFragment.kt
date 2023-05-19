package com.kimphuong.manage.ui.home.calender

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.base.getStringMonth
import com.kimphuong.manage.base.isToday
import com.kimphuong.manage.base.toMoney
import com.kimphuong.manage.databinding.FragmentHomeCalenderBinding
import com.kimphuong.manage.db.entity.DayData
import com.kimphuong.manage.db.entity.TransactionDetail
import com.kimphuong.manage.ui.main.MainViewModel
import java.util.*

class HomeCalenderFragment : BaseFragment<MainViewModel, FragmentHomeCalenderBinding>(
    MainViewModel::class.java
) {
    private var calendar = Calendar.getInstance()
    var listDataAll = mutableListOf<TransactionDetail>()
    var listDataFilter = mutableListOf<TransactionDetail>()

    lateinit var calendarAdapter: CalendarAdapter

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeCalenderBinding {
        return FragmentHomeCalenderBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        calendarAdapter = CalendarAdapter(requireContext(), listOf())
        binding.rcvMonthData.apply {
            adapter = calendarAdapter
            addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
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
        val listDayData = getListDayDataOfMonth()
        calendarAdapter.setData(listDayData)
    }

    private fun getListDayDataOfMonth(): List<DayData> {
        val tempCalendar = Calendar.getInstance()
        tempCalendar.set(Calendar.DAY_OF_MONTH, 1)
        tempCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
        tempCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
        var day = tempCalendar.get(Calendar.DAY_OF_WEEK)
        tempCalendar.add(Calendar.DAY_OF_MONTH, 1 - day)
        val firstCalendar = tempCalendar.clone() as Calendar
        tempCalendar.set(
            Calendar.DAY_OF_MONTH,
            calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        )
        tempCalendar.set(
            Calendar.MONTH,
            calendar.get(Calendar.MONTH)
        )
        day = tempCalendar.get(Calendar.DAY_OF_WEEK)
        tempCalendar.add(Calendar.DAY_OF_MONTH, 7 - day)
        val lastCalendar = tempCalendar.clone() as Calendar
        var listDayData = arrayListOf<DayData>()
        while (firstCalendar.timeInMillis <= lastCalendar.timeInMillis) {
            Log.d("abcc", "getListDayDataOfMonth: ${firstCalendar.timeInMillis}, ${lastCalendar.timeInMillis}")
            val day = firstCalendar.get(Calendar.DAY_OF_MONTH)
            val month = firstCalendar.get(Calendar.MONTH)
            val sumIncome =
                listDataFilter.filter { it.day == day && it.month == month && it.type }
                    .sumOf { it.amount.toInt() }
            val sumExpend =
                listDataFilter.filter { it.day == day && it.month == month && !it.type }
                    .sumOf { it.amount.toInt() }
            listDayData.add(DayData(day, sumIncome, sumExpend,firstCalendar.isToday()))
            firstCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        return listDayData
    }

    fun updateDataFollowMonth(i: Int) {
        calendar.add(Calendar.MONTH, i)
        calculateData()
    }

    fun getCurrentMonth(context: Context) =
        "${context.getStringMonth(calendar.get(Calendar.MONTH))} ${calendar.get(Calendar.YEAR)}"
}