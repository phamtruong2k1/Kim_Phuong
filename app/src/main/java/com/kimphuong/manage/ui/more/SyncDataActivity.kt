package com.kimphuong.manage.ui.more

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.base.getInstanceProgressBar
import com.kimphuong.manage.base.showAlertDialog
import com.kimphuong.manage.base.showDialogConfirm
import com.kimphuong.manage.databinding.ActivitySyncDataBinding
import com.kimphuong.manage.ui.main.MainViewModel
import com.kimphuong.manage.utils.setOnSafeClick
import java.io.File

class SyncDataActivity :
    BaseActivity<MainViewModel, ActivitySyncDataBinding>(MainViewModel::class.java) {

    private var dialogProgressBar: Dialog? = null

    override fun inflateViewBinding(inflater: LayoutInflater): ActivitySyncDataBinding {
        return ActivitySyncDataBinding.inflate(layoutInflater)
    }

    override fun initView() {
        dialogProgressBar = getInstanceProgressBar()

    }

    override fun initData() {
        viewModel.getAllAccountEntity().observe(this) {
            viewModel.setListAccount(it)
        }
        viewModel.getAllTransactionEntity().observe(this) {
            viewModel.setListTransaction(it)
        }
        viewModel.getAllCategoryEntity().observe(this) {
            viewModel.setListCategory(it)
        }
        viewModel.getAllTypeAccountEntity().observe(this) {
            viewModel.setListTypeAccount(it)
        }

        viewModel.stateShowProgressBar.observe(this) {
            if (it) {
                dialogProgressBar?.show()
            } else {
                dialogProgressBar?.dismiss()
            }
        }
        viewModel.stateRestore.observe(this) {
            showAlertDialog(
                message = if (it) getString(R.string.restore_success) else getString(
                    R.string.restore_failed
                )
            )
        }
    }

    override fun initListener() {
        binding.llBackup.setOnSafeClick {
            Log.d("abcc", "initListener: ")
            viewModel.doSaveTxt {
                it?.let { it1 -> shareFile(it1) }
            }
        }

        binding.llRestore.setOnSafeClick {
            handleSelectFileRestore()
        }

        binding.imgBack.setOnSafeClick {
            finish()
        }
        binding.llDeleteAllData.setOnSafeClick {
            showDialogConfirm(
                getString(R.string.delete_all_confirm),
                "OK",
                "Cancel",
                callbackPos = {
                    viewModel.deleteAllData()
                    showAlertDialog(message = "Delete success")
                })
        }
    }

    override fun initViewModel(viewModel: MainViewModel) {

    }

    private fun shareFile(file: File) {
        val uri =
            FileProvider.getUriForFile(this, getString(R.string.author_name), file)
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_STREAM, uri)
        }
        startActivity(Intent.createChooser(intent, getString(R.string.app_name)))
    }


    private fun handleSelectFileRestore() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "text/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        selectFileLauncher.launch(
            Intent.createChooser(
                intent, getString(R.string.select_file)
            )
        )
    }

    private var selectFileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let {
                    showDialogConfirm(
                        getString(R.string.restore_file_confirm),
                        getString(R.string.restore),
                        "Cancel",
                        callbackPos = {
                            viewModel.readBackupFile(it, null)
                        })
                }
            }
        }
}