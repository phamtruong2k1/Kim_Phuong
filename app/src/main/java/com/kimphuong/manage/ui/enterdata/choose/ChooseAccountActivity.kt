package com.kimphuong.manage.ui.enterdata.choose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivityChooseAccountBinding
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.ui.account.AddAccountActivity
import com.kimphuong.manage.ui.account.ShowAccountAdapter
import com.kimphuong.manage.ui.account.ShowAccountAdapterListener
import com.kimphuong.manage.utils.DataUtil
import com.kimphuong.manage.utils.DialogUtil
import com.kimphuong.manage.utils.EventActionEditAndDelete
import com.kimphuong.manage.utils.openActivity

class ChooseAccountActivity : BaseActivity<ChooseDataViewModel, ActivityChooseAccountBinding>(ChooseDataViewModel::class.java) {

    override fun initViewModel(viewModel: ChooseDataViewModel) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityChooseAccountBinding {
        return ActivityChooseAccountBinding.inflate(layoutInflater)
    }

    lateinit var adapter: ShowAccountAdapter

    override fun initView() {
        adapter = ShowAccountAdapter(this, ArrayList(), ArrayList(), object :
            ShowAccountAdapterListener {
            override fun click(account : AccountEntity) {
                val data = Intent()
                data.putExtra("data", account.toJson())
                setResult(111, data)
                finish()
            }

            override fun longClick(account: AccountEntity) {
                DialogUtil.showDialogBottomSheetEditAndDelete(this@ChooseAccountActivity, object :
                    EventActionEditAndDelete {
                    override fun edit() {
                        val intent = Intent(this@ChooseAccountActivity, AddAccountActivity::class.java)
                        intent.putExtra("action", "edit")
                        intent.putExtra("data", account.toJson())
                        startActivity(intent)
                    }

                    override fun delete() {
                        viewModel.deleteAccount(account)
                    }
                })
            }
        })

        binding.rcyAccount.adapter = adapter
    }

    override fun initData() {
        viewModel.getAllAccount().observe(this) { data ->
            adapter.setData(
                DataUtil.listTypeAccount,
                data
            )
        }
    }

    override fun initListener() {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

        binding.imgAdd.setOnClickListener {
            openActivity(AddAccountActivity::class.java)
        }
    }
}