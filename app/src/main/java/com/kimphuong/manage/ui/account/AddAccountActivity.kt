package com.kimphuong.manage.ui.account

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivityAddAccountBinding
import com.kimphuong.manage.databinding.ActivitySplashBinding
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.ui.splash.SplashViewModel
import com.kimphuong.manage.utils.DataUtil
import com.kimphuong.manage.utils.showToast
import java.lang.Exception

class AddAccountActivity : BaseActivity<AccountViewModel, ActivityAddAccountBinding>(AccountViewModel::class.java) {

    var account = AccountEntity()

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityAddAccountBinding {
        return ActivityAddAccountBinding.inflate(layoutInflater)
    }

    override fun initView() {
        showNameCate()
    }

    override fun initData() {

    }

    override fun initListener() {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

        binding.edtAccount.setOnClickListener {
            showDialogChooseTypeAccount()
        }

        binding.txtSave.setOnClickListener {
            if (binding.edtAmount.text.toString().trim().isEmpty()
                || binding.edtName.text.toString().trim().isEmpty()
            ) {
                showToast("Data must not null.")
            } else {
                account.name = binding.edtName.text.toString()
                try {
                    account.amount = binding.edtAmount.text.toString().trim().toFloat()

                } catch(e : Exception) {
                    showToast("Amount must be a number.")
                }
                viewModel.addAccount(account)
                finish()
            }
        }
    }

    fun showNameCate() {
        binding.edtAccount.setText(DataUtil.listTypeAccount[account.type_account_id-1].name)
    }

    private fun showDialogChooseTypeAccount() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.layout_dialog_choose_type_account)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val adapter = TypeAccountAdapter(this, DataUtil.listTypeAccount, account.type_account_id -1, object : TypeAccountAdapterListener{
            override fun click(position: Int) {
                account.type_account_id = position+1
                dialog.dismiss()
                showNameCate()
            }
        })
        dialog.findViewById<RecyclerView>(R.id.rcyTypeAccount).adapter = adapter
        dialog.show()
    }

    override fun initViewModel(viewModel: AccountViewModel) {

    }
}