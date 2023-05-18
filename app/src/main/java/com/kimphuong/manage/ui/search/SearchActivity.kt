package com.kimphuong.manage.ui.search

import android.content.Intent
import android.view.LayoutInflater
import androidx.core.widget.doOnTextChanged
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivitySearchBinding
import com.kimphuong.manage.db.entity.TransactionDetail
import com.kimphuong.manage.ui.enterdata.EnterDataActivity
import com.kimphuong.manage.ui.home.daily.DailyDataAdapter
import com.kimphuong.manage.ui.main.MainViewModel
import com.kimphuong.manage.utils.setOnSafeClick

class SearchActivity :
    BaseActivity<MainViewModel, ActivitySearchBinding>(MainViewModel::class.java) {

    private var listDataAll = listOf<TransactionDetail>()

    private var currentQuery = ""

    private lateinit var dailyDataAdapter: DailyDataAdapter
    override fun initViewModel(viewModel: MainViewModel) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivitySearchBinding {
        return ActivitySearchBinding.inflate(layoutInflater)
    }

    override fun initView() {
        dailyDataAdapter = DailyDataAdapter(this, listOf()){
            val intent = Intent(this, EnterDataActivity::class.java)
            intent.putExtra("transaction", it)
            startActivity(intent)
        }
        binding.rcvSearch.apply {
            adapter = dailyDataAdapter
            setHasFixedSize(true)
        }
    }

    override fun initData() {
    }

    override fun initListener() {
        binding.edtSearch.doOnTextChanged { text, start, before, count ->
            searchData(text.toString())
        }
        binding.imgBack.setOnSafeClick {
            onBackPressed()
        }
        viewModel.getAllTransactionDetail().observe(this) {
            listDataAll = it
            searchData(currentQuery)
        }
    }

    private fun searchData(text: String) {
        if (text.isEmpty()){
            dailyDataAdapter.setData(listOf(),true)
        }else{
            val listSearch = listDataAll.filter {
                it.note.contains(text.toString())
            }
            dailyDataAdapter.setData(listSearch,true)
            currentQuery = ""
        }
    }
}