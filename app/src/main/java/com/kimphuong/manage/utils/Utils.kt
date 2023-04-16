package com.kimphuong.manage.utils

import android.content.Context
import com.kimphuong.manage.R
import java.util.*

fun getCurrentYear(): Int = Calendar.getInstance().get(Calendar.YEAR)
fun getCurrentMonthInt(): Int = Calendar.getInstance().get(Calendar.MONTH) + 1
fun getCurrentDayOfMonth(): Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

fun Context.getCurrentDaily(): String {
    val listOfMonth = mutableListOf(
        getString(R.string.january),
        getString(R.string.february),
        getString(R.string.march),
        getString(R.string.april),
        getString(R.string.may),
        getString(R.string.june),
        getString(R.string.july),
        getString(R.string.august),
        getString(R.string.september),
        getString(R.string.october),
        getString(R.string.november),
        getString(R.string.december)
    )
    val day = getCurrentDayOfMonth()
    val month = getCurrentMonthInt()
    val year = getCurrentYear()
    return if (day < 10) {
        "0$day ${listOfMonth[month - 1]} $year"
    } else {
        "$day ${listOfMonth[month - 1]} $year"
    }
}

fun Context.getCurrentMonthly(): String {
    val listOfMonth = mutableListOf(
        getString(R.string.january),
        getString(R.string.february),
        getString(R.string.march),
        getString(R.string.april),
        getString(R.string.may),
        getString(R.string.june),
        getString(R.string.july),
        getString(R.string.august),
        getString(R.string.september),
        getString(R.string.october),
        getString(R.string.november),
        getString(R.string.december)
    )
    val month = getCurrentMonthInt()
    val year = getCurrentYear()
    return "${listOfMonth[month - 1]} $year"
}

fun Context.getCurrentToday(): String {
    val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    val listOfDay = mutableListOf(
        getString(R.string.sunday),
        getString(R.string.monday),
        getString(R.string.tuesday),
        getString(R.string.wednesday),
        getString(R.string.thursday),
        getString(R.string.friday),
        getString(R.string.saturday)
    )
    val listOfMonth = mutableListOf(
        getString(R.string.january),
        getString(R.string.february),
        getString(R.string.march),
        getString(R.string.april),
        getString(R.string.may),
        getString(R.string.june),
        getString(R.string.july),
        getString(R.string.august),
        getString(R.string.september),
        getString(R.string.october),
        getString(R.string.november),
        getString(R.string.december)
    )
    val txtDay = listOfDay[day - 1]
    val month = getCurrentMonthInt()
    val year = getCurrentYear()
    return if (day < 10) {
        "0$day ${listOfMonth[month - 1]} $year, $txtDay"
    } else {
        "$day ${listOfMonth[month - 1]} $year, $txtDay"
    }
}
