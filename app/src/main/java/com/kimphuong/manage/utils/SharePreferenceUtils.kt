package com.kimphuong.manage.utils

import android.content.Context

object SharePreferenceUtils {

    val PER_NAME = "data_app_meme_sound"

    //login_ed
    fun setUserLogin(context: Context, data: Boolean) {
        val pre = context.getSharedPreferences(PER_NAME, Context.MODE_PRIVATE)
        pre.edit().putBoolean("login_ed", data).apply()
    }

    fun getUserLogin(context: Context): Boolean {
        val pre = context.getSharedPreferences(PER_NAME, Context.MODE_PRIVATE)
        return pre.getBoolean("login_ed", false)
    }
}