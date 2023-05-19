package com.kimphuong.manage.ui.enterdata.choose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.base.showDialogConfirm
import com.kimphuong.manage.databinding.ActivityChooseAccountBinding
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.TransactionEntity
import com.kimphuong.manage.db.entity.TypeAccountEntity
import com.kimphuong.manage.ui.account.AccountViewModel
import com.kimphuong.manage.ui.account.AddAccountActivity
import com.kimphuong.manage.ui.account.ShowAccountAdapter
import com.kimphuong.manage.ui.account.ShowAccountAdapterListener
import com.kimphuong.manage.utils.DataUtil
import com.kimphuong.manage.utils.DialogUtil
import com.kimphuong.manage.utils.EventActionEditAndDelete
import com.kimphuong.manage.utils.openActivity

class ChooseAccountActivity : BaseActivity<AccountViewModel, ActivityChooseAccountBinding>( AccountViewModel::class.java) {

    override fun initViewModel(viewModel:  AccountViewModel) {

    }
    private var listTransaction = listOf<TransactionEntity>()
    private var listAccount = listOf<AccountEntity>()

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityChooseAccountBinding {
        return ActivityChooseAccountBinding.inflate(layoutInflater)
    }

    lateinit var adapter: ShowAccountAdapter

    override fun initView() {
        adapter = ShowAccountAdapter(
            this,
            listOf(),
            object : ShowAccountAdapterListener {
                override fun click(account: AccountEntity) {
                    val data = Intent()
                    data.putExtra("data", account.toJson())
                    setResult(111, data)
                    finish()
                }

                override fun longClick(account: AccountEntity) {
                    DialogUtil.showDialogBottomSheetEditAndDelete(
                        this@ChooseAccountActivity,
                        object : EventActionEditAndDelete {
                            override fun edit() {
                                val intent =
                                    Intent(this@ChooseAccountActivity, AddAccountActivity::class.java)
                                intent.putExtra("action", "edit")
                                intent.putExtra("data", account.toJson())
                                startActivity(intent)
                            }

                            override fun delete() {
                                showDialogConfirm(
                                    getString(R.string.delete_this_data_confirm),
                                    "OK",
                                    "Cancel",
                                    callbackPos = {
                                        viewModel?.deleteAccount(account)
                                    })
                            }
                        })
                }
            })

        binding.rcyAccount.adapter = adapter
    }

    override fun initData() {
        viewModel?.getAllTransaction()?.observe(this) {
            listTransaction = it
            updateData()
        }
        viewModel?.getAllAccount()?.observe(this) {
            listAccount = it
            updateData()
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


    private fun updateData() {
        val setTypeAcc = HashSet<Int>()
        listAccount.forEach { acc ->
            setTypeAcc.add(acc.type_account_id)
            acc.amount = acc.amount + listTransaction.filter { it.account_id == acc.account_id && it.type }
                .sumOf { it.amount.toInt() }
            acc.amount = acc.amount - listTransaction.filter { it.account_id == acc.account_id && !it.type }
                .sumOf { it.amount.toInt() }
        }

        val listData = arrayListOf<Pair<TypeAccountEntity, List<AccountEntity>>>()
        setTypeAcc.forEach { typeId ->
            val type = DataUtil.listTypeAccount[typeId - 1]
            val listAcc = listAccount.filter { it.type_account_id == typeId }
            listData.add(Pair(type, listAcc))
        }
        adapter.setData(listData)
    }

}