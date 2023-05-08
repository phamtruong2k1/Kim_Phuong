package com.kimphuong.manage.utils

import com.kimphuong.manage.R
import com.kimphuong.manage.db.entity.TypeAccountEntity

object DataUtil {

    var listTypeAccount = listOf<TypeAccountEntity>(
        TypeAccountEntity(1,"Cash", R.drawable.ic_cash),
        TypeAccountEntity(2,"Bank account", R.drawable.ic_bank_account),
        TypeAccountEntity(3,"Credit card", R.drawable.ic_credit_card),
        TypeAccountEntity(4,"Debit card", R.drawable.ic_debit_card),
        TypeAccountEntity(5,"Investment", R.drawable.ic_investment),
        TypeAccountEntity(6,"Insurance", R.drawable.ic_insurance),
        TypeAccountEntity(7,"Loan", R.drawable.ic_loan),
        TypeAccountEntity(8,"Other", R.drawable.ic_other_account)
    )

}