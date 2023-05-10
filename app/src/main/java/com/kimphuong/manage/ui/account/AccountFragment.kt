package com.kimphuong.manage.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.databinding.FragmentAccountBinding
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.utils.DataUtil
import com.kimphuong.manage.utils.DialogUtil
import com.kimphuong.manage.utils.EventActionEditAndDelete
import com.kimphuong.manage.utils.openActivity

class AccountFragment : BaseFragment<AccountViewModel, FragmentAccountBinding>(AccountViewModel::class.java) {

    lateinit var adapter: ShowAccountAdapter

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAccountBinding {
        return FragmentAccountBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        adapter = ShowAccountAdapter(requireContext(), ArrayList(), ArrayList(), object : ShowAccountAdapterListener{
            override fun click(account : AccountEntity) {

            }

            override fun longClick(account: AccountEntity) {
                DialogUtil.showDialogBottomSheetEditAndDelete(requireActivity(), object : EventActionEditAndDelete{
                    override fun edit() {
                        val intent = Intent(requireContext(), AddAccountActivity::class.java)
                        intent.putExtra("action", "edit")
                        intent.putExtra("data", account.toJson())
                        startActivity(intent)
                    }

                    override fun delete() {
                        viewModel?.deleteAccount(account)
                    }
                })
            }
        })

        binding.rcyAccount.adapter = adapter
    }

    override fun initData() {
        viewModel?.getAllAccount()?.observe(this) { data ->
            adapter.setData(
                DataUtil.listTypeAccount,
                data
            )
            var asset = 0f
            var expense = 0f
            data.forEach {
                if (it.amount > 0) {
                    asset += it.amount
                } else {
                    expense += it.amount
                }
            }
            binding.txtAsset.text = asset.toString()
            binding.txtExpense.text = expense.toString()
            binding.txtBalance.text = (asset + expense).toString()
        }
    }

    override fun initListener() {
        binding.imgAdd.setOnClickListener {
            requireContext().openActivity(AddAccountActivity::class.java)
        }
    }


}