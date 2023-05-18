package com.kimphuong.manage.base

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.kimphuong.manage.R
import java.util.*

fun Calendar.toDateMonth(): String {
    return "${this.get(Calendar.DAY_OF_MONTH)}/${this.get(Calendar.MONTH) + 1}"
}

fun Context.getStringMonth(index: Int): String {
    return if (index in 0..11) {
        this.resources.getStringArray(R.array.array_month).toList()[index]
    } else index.toString()
}

fun Context.showDialogConfirm(
    message: String,
    positiveBtnStr: String,
    negativeBtnStr: String,
    callbackPos: () -> Unit = {}
) {
    val builder = AlertDialog.Builder(this)
    builder.setMessage(message)
    builder.setPositiveButton(positiveBtnStr) { dialog, _ ->
        callbackPos()
        dialog.dismiss()
    }

    builder.setNegativeButton(negativeBtnStr) { dialog, _ ->
        dialog.dismiss()
    }
    builder.setCancelable(false)
    val alert = builder.create()
    alert.show()
    val positive = alert.getButton(AlertDialog.BUTTON_POSITIVE)
    val negative = alert.getButton(AlertDialog.BUTTON_NEGATIVE)
    negative.isSingleLine = true
    positive.isSingleLine = true

}

fun Calendar.isToday(): Boolean {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.YEAR) == this.get(Calendar.YEAR) && calendar.get(Calendar.MONTH) == this.get(
        Calendar.MONTH
    ) && calendar.get(Calendar.DAY_OF_MONTH) == this.get(Calendar.DAY_OF_MONTH)
}

fun Calendar.toDateString():String{
    return "${this.get(Calendar.DAY_OF_MONTH)}/${this.get(Calendar.MONTH)+1}/${this.get(Calendar.YEAR)}"
}