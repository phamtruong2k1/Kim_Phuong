package com.kimphuong.manage.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kimphuong.manage.R
import com.kimphuong.manage.databinding.ProgressBlockingUserBinding
import java.text.DecimalFormat
import java.text.SimpleDateFormat
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

fun Activity.getInstanceProgressBar(callback: ((ProgressBar) -> Unit)? = null): Dialog {
    val builder = AlertDialog.Builder(this)
    val binding = ProgressBlockingUserBinding.inflate(LayoutInflater.from(this))
    callback?.invoke(binding.pbBlocking)
    builder.setView(binding.root)
    val dialog: AlertDialog = builder.create()
    dialog.setCancelable(false)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setCanceledOnTouchOutside(false)
    return dialog
}

fun Calendar.parseToString(format: String): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    return sdf.format(this.time)
}

fun Int.toMoney():String{
    val formatter = DecimalFormat("#,###")
    return formatter.format(this.toDouble()).replace(',','.')
}



fun Context.showAlertDialog(
    title: String? = null,
    message: String? = null,
    textPosBtn: String = "OK",
    callback: () -> Unit = {}
) {
    val builder = AlertDialog.Builder(this)
    builder.apply {
        title?.let {
            setTitle(it)
        }
        message?.let {
            setMessage(it)
        }
        setPositiveButton(textPosBtn) { _, _ ->
            callback()
        }
        show()
    }
}