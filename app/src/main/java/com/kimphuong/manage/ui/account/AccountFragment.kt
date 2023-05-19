package com.kimphuong.manage.ui.account

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.base.showDialogConfirm
import com.kimphuong.manage.base.toMoney
import com.kimphuong.manage.databinding.FragmentAccountBinding
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.TransactionEntity
import com.kimphuong.manage.db.entity.TypeAccountEntity
import com.kimphuong.manage.utils.DataUtil
import com.kimphuong.manage.utils.DialogUtil
import com.kimphuong.manage.utils.EventActionEditAndDelete
import com.kimphuong.manage.utils.openActivity

class AccountFragment :
    BaseFragment<AccountViewModel, FragmentAccountBinding>(AccountViewModel::class.java) {

    lateinit var adapter: ShowAccountAdapter

    private var listTransaction = listOf<TransactionEntity>()

    private var listAccount = listOf<AccountEntity>()

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAccountBinding {
        return FragmentAccountBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        adapter = ShowAccountAdapter(
            requireContext(),
            listOf(),
            object : ShowAccountAdapterListener {
                override fun click(account: AccountEntity) {

                }

                override fun longClick(account: AccountEntity) {
                    DialogUtil.showDialogBottomSheetEditAndDelete(
                        requireActivity(),
                        object : EventActionEditAndDelete {
                            override fun edit() {
                                val intent =
                                    Intent(requireContext(), AddAccountActivity::class.java)
                                intent.putExtra("action", "edit")
                                intent.putExtra("data", account.toJson())
                                startActivity(intent)
                            }

                            override fun delete() {
                                requireContext().showDialogConfirm(
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

    private fun updateData() {
        val setTypeAcc = HashSet<Int>()
        listAccount.forEach { acc ->
            setTypeAcc.add(acc.type_account_id)
            acc.amount = acc.amount + listTransaction.filter { it.account_id == acc.account_id && it.type }
                .sumOf { it.amount.toInt() } - listTransaction.filter { it.account_id == acc.account_id && !it.type }
                .sumOf { it.amount.toInt() }

        }

        val listData = arrayListOf<Pair<TypeAccountEntity, List<AccountEntity>>>()
        setTypeAcc.forEach { typeId ->
            val type = DataUtil.listTypeAccount[typeId - 1]
            val listAcc = listAccount.filter { it.type_account_id == typeId }
            listData.add(Pair(type, listAcc))
        }

        adapter.setData(listData)
        var asset = 0
        var expense = 0
        listAccount.forEach {
            asset += it.amount.toInt()
        }

        expense = listTransaction.filter { !it.type }.sumOf { it.amount.toInt() }
        binding.txtAsset.text = asset.toMoney()
        binding.txtExpense.text = expense.toMoney()
        binding.txtBalance.text = (asset - expense).toMoney()
    }

    override fun initListener() {
        binding.imgAdd.setOnClickListener {
            requireContext().openActivity(AddAccountActivity::class.java)
        }
    }


}