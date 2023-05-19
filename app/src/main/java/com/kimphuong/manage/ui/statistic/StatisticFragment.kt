package com.kimphuong.manage.ui.statistic

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.base.getStringMonth
import com.kimphuong.manage.base.toDateString
import com.kimphuong.manage.databinding.FragmentStatisticBinding
import com.kimphuong.manage.db.entity.DataStatistic
import com.kimphuong.manage.db.entity.TransactionDetail
import com.kimphuong.manage.ui.main.MainViewModel
import com.kimphuong.manage.utils.Constant.LIST_COLOR
import com.kimphuong.manage.utils.setOnSafeClick
import java.util.*


class StatisticFragment : BaseFragment<MainViewModel, FragmentStatisticBinding>(
    MainViewModel::class.java
) {

    private var listDataAll = listOf<TransactionDetail>()
    private var listDataFilter = listOf<TransactionDetail>()

    private var calendar = Calendar.getInstance()

    private var calFirstWeek = Calendar.getInstance()

    private var isIncome = true

    private var currentFilter = R.id.monthly

    lateinit var statisticAdapter: StatisticAdapter
    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStatisticBinding {
        return FragmentStatisticBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        getCalFirstWeek()
        binding.tvPeriod.text = "Monthly"
        binding.tvDate.text = "${requireContext().getStringMonth(calendar.get(Calendar.MONTH))} ${
            calendar.get(Calendar.YEAR)
        }"
        binding.pieChart.apply {
            renderer = CustomPieChart(this, this.animator, this.viewPortHandler)
        }
        statisticAdapter = StatisticAdapter(requireContext(), listOf(), 0f)
        binding.rcvStatistic.apply {
            adapter = statisticAdapter
            setHasFixedSize(true)
        }
    }

    private fun getCalFirstWeek() {
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        calFirstWeek.add(Calendar.DAY_OF_MONTH, Calendar.MONDAY - day)
    }

    override fun initData() {
    }

    override fun initListener() {
        binding.layoutPeriod.setOnSafeClick {
            val popupMenu = PopupMenu(requireContext(), binding.layoutPeriod)

            // Inflating popup menu from popup_menu.xml file
            popupMenu.menuInflater.inflate(R.menu.menu_statistic, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.yearly -> {
                        binding.tvPeriod.text = "Yearly"
                        binding.tvDate.text = calendar.get(Calendar.YEAR).toString()
                        currentFilter = R.id.yearly
                    }

                    R.id.monthly -> {
                        binding.tvPeriod.text = "Monthly"
                        binding.tvDate.text =
                            "${requireContext().getStringMonth(calendar.get(Calendar.MONTH))} ${
                                calendar.get(Calendar.YEAR)
                            }"
                        currentFilter = R.id.monthly
                    }

                    R.id.weekly -> {
                        binding.tvPeriod.text = "Weekly"
                        val calNextWeek = calFirstWeek.clone() as Calendar
                        val dateFirst = calFirstWeek.toDateString()
                        calNextWeek.add(Calendar.DAY_OF_MONTH, 7)
                        val dateNextWeek = calNextWeek.toDateString()
                        binding.tvDate.text = "$dateFirst - $dateNextWeek"
                        currentFilter = R.id.weekly
                    }
                }
                filterData()
                true
            }
            popupMenu.show()
        }
        viewModel?.getAllTransactionDetail()?.observe(this) {
            listDataAll = it
            filterData()
        }

        binding.tvIncome.setOnSafeClick {
            isIncome = true
            binding.spaceIncome.setBackgroundColor(Color.WHITE)
            binding.spaceExpend.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_app
                )
            )
            filterData()
        }

        binding.tvExpend.setOnSafeClick {
            isIncome = false
            binding.spaceExpend.setBackgroundColor(Color.WHITE)
            binding.spaceIncome.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_app
                )
            )
            filterData()
        }

        binding.btnBackDate.setOnSafeClick {
            when (currentFilter) {
                R.id.yearly -> {
                    calendar.add(Calendar.YEAR, -1)
                    binding.tvDate.text = calendar.get(Calendar.YEAR).toString()
                }

                R.id.monthly -> {
                    calendar.add(Calendar.MONTH, -1)
                    binding.tvDate.text =
                        "${requireContext().getStringMonth(calendar.get(Calendar.MONTH))} ${
                            calendar.get(Calendar.YEAR)
                        }"
                }

                R.id.weekly -> {
                    calFirstWeek.add(Calendar.DAY_OF_MONTH, -7)
                    val calNextWeek = calFirstWeek.clone() as Calendar
                    calNextWeek.add(Calendar.DAY_OF_MONTH, 7)
                    val dateFirst = calFirstWeek.toDateString()
                    val dateNextWeek = calNextWeek.toDateString()
                    binding.tvDate.text = "$dateFirst - $dateNextWeek"
                }
            }
            filterData()
        }

        binding.btnNextDate.setOnSafeClick {
            when (currentFilter) {
                R.id.yearly -> {
                    calendar.add(Calendar.YEAR, 1)
                    binding.tvDate.text = calendar.get(Calendar.YEAR).toString()
                }

                R.id.monthly -> {
                    calendar.add(Calendar.MONTH, 1)
                    binding.tvDate.text =
                        "${requireContext().getStringMonth(calendar.get(Calendar.MONTH))} ${
                            calendar.get(Calendar.YEAR)
                        }"
                }

                R.id.weekly -> {
                    calFirstWeek.add(Calendar.DAY_OF_MONTH, 7)
                    val calNextWeek = calFirstWeek.clone() as Calendar
                    calNextWeek.add(Calendar.DAY_OF_MONTH, 7)
                    val dateFirst = calFirstWeek.toDateString()
                    val dateNextWeek = calNextWeek.toDateString()
                    binding.tvDate.text = "$dateFirst - $dateNextWeek"
                }
            }
            filterData()
        }

    }

    private fun filterData() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val calNextWeek = calFirstWeek.clone() as Calendar
        calNextWeek.add(Calendar.DAY_OF_MONTH, 7)
        when (currentFilter) {
            R.id.yearly -> {
                listDataFilter = listDataAll.filter { it.year == year && it.type == isIncome }
            }
            R.id.monthly -> {
                listDataFilter =
                    listDataAll.filter { it.year == year && it.month == month && it.type == isIncome }
            }
            R.id.weekly -> {
                listDataFilter =
                    listDataAll.filter {
                        val cal = Calendar.getInstance()
                        cal.set(it.year, it.month, it.day)
                        it.year == calFirstWeek.get(Calendar.YEAR) && (cal.timeInMillis >= calFirstWeek.timeInMillis && cal.timeInMillis <= calNextWeek.timeInMillis) && it.type == isIncome
                    }
            }
        }

        val setCategory = HashSet<Pair<Int, String>>()
        var sum = 0f
        listDataFilter.forEach {
            sum += it.amount
            setCategory.add(Pair(it.category_icon, it.category_name))
        }
        val listData = arrayListOf<PieEntry>()
        val listDataStatistic = arrayListOf<DataStatistic>()
        setCategory.forEachIndexed { index, category ->
            val value = listDataFilter.filter { it.category_icon == category.first }
                .sumOf { it.amount.toInt() }
            val drawable = ContextCompat.getDrawable(requireContext(), category.first)
            val bitmap = (drawable as BitmapDrawable).bitmap
            val newDrawable =
                BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 20, 20, true))
            listData.add(PieEntry(value.toFloat(), category.second, newDrawable))
            listDataStatistic.add(
                DataStatistic(
                    value,
                    drawable,
                    category.second,
                    LIST_COLOR[index]
                )
            )
        }
        statisticAdapter.setData(listDataStatistic, sum)
        if (listData.size != 0) {
            binding.pieChart.visibility = View.VISIBLE
            filDataToChart(listData)
            binding.noData.visibility = View.GONE
        } else {
            binding.noData.visibility = View.VISIBLE
            binding.pieChart.visibility = View.GONE
        }
    }


    private fun filDataToChart(dataEntry: ArrayList<PieEntry>) {
        binding.pieChart.isRotationEnabled = false
//        dataEntry.add(PieEntry(945f, "icon1", 0f))
//        dataEntry.add(PieEntry(1040f, "icon2", 1f))
//        dataEntry.add(PieEntry(1133f, "icon3", 2f))
//        dataEntry.add(PieEntry(1240f, "icon4", 3f))
//        dataEntry.add(PieEntry(1369f, "icon5", 4f))
//        dataEntry.add(PieEntry(1487f, "icon6", 5f))
//        dataEntry.add(PieEntry(1501f, "icon7", 6f))
//        dataEntry.add(PieEntry(1645f, "icon8", 7f))
//        dataEntry.add(PieEntry(1578f, "icon9", 8f))
//        dataEntry.add(PieEntry(1695f, "icon10", 9f))
        val listColorValue = arrayListOf<Int>()
        repeat(dataEntry.size) {
            listColorValue.add(Color.BLACK)
        }
        val dataSet = PieDataSet(dataEntry, "")
        dataSet.apply {
            setDrawValues(true)
            setValueTextColors(listColorValue)
            valueTextSize = 12f
            formSize = 5f
            valueFormatter = PercentFormatter(binding.pieChart)
        }

        val pieData = PieData(dataSet)
        binding.pieChart.apply {
            description.isEnabled = false
            setUsePercentValues(true)
            this.data = pieData
            legend.isEnabled = false
            setEntryLabelColor(Color.BLACK)
        }
        dataSet.colors = LIST_COLOR
        binding.pieChart.animateXY(500, 500)
    }

}