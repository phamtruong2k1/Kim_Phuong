package com.kimphuong.manage.ui.enterdata

import android.app.DatePickerDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.base.showDialogConfirm
import com.kimphuong.manage.databinding.ActivityEnterDataBinding
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.CategoryEntity
import com.kimphuong.manage.db.entity.TransactionEntity
import com.kimphuong.manage.ui.enterdata.choose.ChooseAccountActivity
import com.kimphuong.manage.ui.enterdata.choose.ChooseCategoryActivity
import com.kimphuong.manage.utils.setOnSafeClick
import com.kimphuong.manage.utils.showToast
import java.util.*

class EnterDataActivity :
    BaseActivity<EnterDataViewModel, ActivityEnterDataBinding>(EnterDataViewModel::class.java),
    DatePickerDialog.OnDateSetListener {


    private var transactionEntity = TransactionEntity()

    var day = 0
    var month: Int = 0
    var year: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0

    override fun initView() {
        var id = intent.getIntExtra("transaction", -1)
        if (id != -1) {
            viewModel.getTransactionById(id).observe(this) {
                if (it!=null){
                    transactionEntity = it
                    binding.imgDelete.visibility = View.VISIBLE
                    viewModel.getCategoryById(it.category_id).observe(this) { category ->
                        binding.edtCategory.setText(category.name)
                    }
                    viewModel.getAccountById(it.account_id).observe(this) { account ->
                        binding.edtAccount.setText(account.name)
                    }
                    fillData()
                }
            }
        }
    }

    private fun fillData() {
        myDay = transactionEntity.day
        myMonth= transactionEntity.month
        myYear = transactionEntity.year
        binding.edtDay.setText("$myDay/${myMonth + 1}/$myYear")
        binding.edtAmount.setText(transactionEntity.amount.toInt().toString())
        binding.edtNote.setText(transactionEntity.note)
    }

    override fun initData() {

    }

    override fun initListener() {
        binding.imgDelete.setOnSafeClick {
            showDialogConfirm("Are you sure to delete this item?", "OK", "Cancel"){
                viewModel.deleteTransaction(transactionEntity)
                finish()
            }
        }
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

        binding.edtAccount.setOnClickListener {
            startActivityForResult(
                Intent(
                    this@EnterDataActivity,
                    ChooseAccountActivity::class.java
                ), 111
            )
        }

        binding.edtCategory.setOnClickListener {
            val intent = Intent(this@EnterDataActivity, ChooseCategoryActivity::class.java)
            intent.putExtra("type", transactionEntity.type)
            startActivityForResult(intent, 222)
        }

        binding.txtIncome.setOnClickListener {
            changeType(true)
        }

        binding.txtExpense.setOnClickListener {
            changeType(false)
        }

        binding.edtDay.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this@EnterDataActivity, this@EnterDataActivity, year, month, day)
            datePickerDialog.show()
        }

        binding.txtSave.setOnClickListener {
            if (transactionEntity.account_id == 0) {
                showToast("Account must not null")
            } else if (transactionEntity.category_id == 0) {
                showToast("Category must not null")
            } else if (binding.edtAmount.text.toString().trim().isEmpty()) {
                showToast("Amount must not null")
            } else {
                transactionEntity.note = binding.edtNote.text.toString()
                transactionEntity.amount = binding.edtAmount.text.toString().toFloat()
                viewModel.saveEnterData(transactionEntity)
                showToast("Success.")
                finish()
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = dayOfMonth
        myYear = year
        myMonth = month
        binding.edtDay.setText("$myDay/${myMonth + 1}/$myYear")
        transactionEntity.day = myDay
        transactionEntity.month = myMonth
        transactionEntity.year = myYear
    }

    private fun changeType(boolean: Boolean) {
        transactionEntity.type = boolean
        transactionEntity.category_id = 0
        binding.edtCategory.setText("")
        if (boolean) {
            binding.txtIncome.setTextColor(getColor(R.color.color_app))
            binding.txtIncome.background = getDrawable(R.drawable.bg_txt_income_choose)

            binding.txtExpense.setTextColor(getColor(R.color.gray2))
            binding.txtExpense.background = getDrawable(R.drawable.bg_txt_not_choose)

            binding.txtTitleAction.text = "Income"
        } else {
            binding.txtIncome.setTextColor(getColor(R.color.gray2))
            binding.txtIncome.background = getDrawable(R.drawable.bg_txt_not_choose)

            binding.txtExpense.setTextColor(getColor(R.color.red))
            binding.txtExpense.background = getDrawable(R.drawable.bg_txt_expense_choose)

            binding.txtTitleAction.text = "Expense"
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

    override fun initViewModel(viewModel: EnterDataViewModel) {}
}