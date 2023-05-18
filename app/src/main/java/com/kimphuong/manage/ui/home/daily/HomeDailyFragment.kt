package com.kimphuong.manage.ui.home.daily

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.databinding.FragmentHomeDailyBinding
import com.kimphuong.manage.db.entity.TransactionDetail
import com.kimphuong.manage.ui.enterdata.EnterDataActivity
import com.kimphuong.manage.ui.main.MainViewModel
import com.kimphuong.manage.utils.Constant
import java.util.*


class HomeDailyFragment : BaseFragment<MainViewModel, FragmentHomeDailyBinding>(
    MainViewModel::class.java
) {

    private var calendar = Calendar.getInstance()

    var listDataAll = mutableListOf<TransactionDetail>()
    var listDataFilter = mutableListOf<TransactionDetail>()

    var dailyDataAdapter: DailyDataAdapter? = null

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeDailyBinding {
        return FragmentHomeDailyBinding.inflate(inflater, container, false)
    }

    var tabKey: Int = 0
    override fun initView() {
        tabKey = arguments?.getInt(Constant.TAB_KEY) ?: Constant.TAB_DAILY
        dailyDataAdapter = DailyDataAdapter(requireContext(), listOf()) {
            val intent = Intent(requireContext(), EnterDataActivity::class.java)
            intent.putExtra("transaction", it)
            startActivity(intent)
        }
        binding.rcvDailyData.apply {
            adapter = dailyDataAdapter
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
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
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        listDataFilter =
            listDataAll.filter { it.day == day && it.month == month && it.year == year }
                .toMutableList()

        val sumIncome = listDataFilter.filter { it.type }.sumOf { it.amount.toInt() }
        val sumExpend = listDataFilter.filter { !it.type }.sumOf { it.amount.toInt() }
        binding.tvIncome.text = sumIncome.toString()
        binding.tvExpense.text = sumExpend.toString()
        binding.tvTotal.text = (sumIncome - sumExpend).toString()
        if (listDataFilter.size != 0) {
            binding.layoutNoData.visibility = View.GONE
            binding.rcvDailyData.visibility = View.VISIBLE
            binding.layoutTop.visibility = View.VISIBLE
        } else {
            binding.layoutNoData.visibility = View.VISIBLE
            binding.rcvDailyData.visibility = View.GONE
            binding.layoutTop.visibility = View.GONE
        }
        dailyDataAdapter?.setData(listDataFilter)
    }

    fun updateDataFollowDay(i: Int) {
        calendar.add(Calendar.DAY_OF_MONTH, i)
        calculateData()
    }
}