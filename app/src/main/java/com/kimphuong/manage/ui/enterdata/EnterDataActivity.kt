package com.kimphuong.manage.ui.enterdata

import android.content.Intent
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.widget.Toast
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivityEnterDataBinding
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.ui.enterdata.choose.ChooseAccountActivity
import com.kimphuong.manage.ui.enterdata.choose.ChooseCategoryActivity

class EnterDataActivity : BaseActivity<EnterDataViewModel, ActivityEnterDataBinding>(EnterDataViewModel::class.java) {




    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

        binding.edtAccount.setOnClickListener {
            startActivityForResult(Intent(this@EnterDataActivity, ChooseAccountActivity::class.java), 111)
        }

        binding.edtCategory.setOnClickListener {
            startActivityForResult(Intent(this@EnterDataActivity, ChooseCategoryActivity::class.java), 222)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111) {
            if (data != null) {
                data.getStringExtra("data")?.let {
                    AccountEntity.toAccountEntity(it).let {

                    }
                }
            }
        } else if (requestCode == 222) {
            if (data != null) {
                data.getStringExtra("data")?.let {
                    AccountEntity.toAccountEntity(it).let {

                    }
                }
            }

        }
    }


    override fun inflateViewBinding(inflater: LayoutInflater): ActivityEnterDataBinding {
        return ActivityEnterDataBinding.inflate(layoutInflater)
    }
    override fun initViewModel(viewModel: EnterDataViewModel) { }
}