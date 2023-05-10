package com.kimphuong.manage.utils

import android.app.Activity
import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kimphuong.manage.databinding.LayoutBottomSheetPostBinding

object DialogUtil {

    fun showDialogBottomSheetEditAndDelete(activity: Activity , event : EventActionEditAndDelete) {
        val bottomSheetBinding = LayoutBottomSheetPostBinding.inflate(activity.layoutInflater)
        val moreBottomSheet =
            BottomSheetDialog(activity)
        moreBottomSheet.setContentView(bottomSheetBinding.root)

        bottomSheetBinding.llDelete.setOnClickListener {
            event.delete()
            moreBottomSheet.dismiss()
        }

        bottomSheetBinding.llEdit.setOnClickListener {
            event.edit()
            moreBottomSheet.dismiss()
        }

        moreBottomSheet.show()
    }

}

interface EventActionEditAndDelete {
    fun edit()
    fun delete()
}