package com.kimphuong.manage.ui.enterdata

import android.content.Intent
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.widget.Toast
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivityEnterDataBinding
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.CategoryEntity
import com.kimphuong.manage.db.entity.TransactionEntity
import com.kimphuong.manage.ui.enterdata.choose.ChooseAccountActivity
import com.kimphuong.manage.ui.enterdata.choose.ChooseCategoryActivity

class EnterDataActivity : BaseActivity<EnterDataViewModel, ActivityEnterDataBinding>(EnterDataViewModel::class.java) {


    val transactionEntity = TransactionEntity()

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
            val intent = Intent(this@EnterDataActivity, ChooseCategoryActivity::class.java)
            intent.putExtra("type", transactionEntity.type)
            startActivityForResult(intent, 222)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111) {
            if (data != null) {
                data.getStringExtra("data")?.let {
                    AccountEntity.toAccountEntity(it)?.let { it1 -> updateUiChooseAccount(it1) }
                }
            }
        } else if (requestCode == 222) {
            if (data != null) {
                data.getStringExtra("data")?.let {
                    CategoryEntity.toCategory(it)?.let { it1 ->
                        updateUiChooseCategory(it1)
                    }
                }
            }

        }
    }

    private fun updateUiChooseAccount(accountEntity: AccountEntity) {
        binding.edtAccount.setText(accountEntity.name)
        transactionEntity.account_id = accountEntity.account_id
    }

    private fun updateUiChooseCategory(categoryEntity: CategoryEntity) {
        binding.edtCategory.setText(categoryEntity.name)
        transactionEntity.category_id = categoryEntity.category_id
    }


    override fun inflateViewBinding(inflater: LayoutInflater): ActivityEnterDataBinding {
        return ActivityEnterDataBinding.inflate(layoutInflater)
    }

    override fun initViewModel(viewModel: EnterDataViewModel) { }
}