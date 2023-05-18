package com.kimphuong.manage.ui.enterdata.choose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivityChooseAccountBinding
import com.kimphuong.manage.databinding.ActivityChooseCategoryBinding
import com.kimphuong.manage.db.entity.CategoryEntity

class ChooseCategoryActivity : BaseActivity<ChooseDataViewModel, ActivityChooseCategoryBinding>(ChooseDataViewModel::class.java) {

    var typeChoose = true

    lateinit var adapter: CategoryAdapter

    override fun initViewModel(viewModel: ChooseDataViewModel) {

    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityChooseCategoryBinding {
        return ActivityChooseCategoryBinding.inflate(layoutInflater)
    }

    override fun initView() {
        typeChoose = intent.getBooleanExtra("type", true)

        adapter = CategoryAdapter(this, listOf(), object : CategoryAdapterListener{
            override fun click(categoryEntity: CategoryEntity) {
                val data = Intent()
                data.putExtra("data", categoryEntity.toJson())
                setResult(111, data)
                finish()
            }

            override fun longClick(categoryEntity: CategoryEntity) {

            }
        })

        binding.rcyCategory.adapter = adapter
    }

    override fun initData() {
        viewModel.getListCategory(typeChoose).observe(this) {
            adapter.setData(it)
        }
    }

    override fun initListener() {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

}