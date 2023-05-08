package com.kimphuong.manage.base

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding>(private val mViewModelClass: Class<VM>) :
    DaggerAppCompatActivity() {

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    protected lateinit var binding: VB

    val viewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactory).get(mViewModelClass)
    }

    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding(layoutInflater)
        setContentView(binding.root)
        initViewModel(viewModel)
        onInject()
        initView()
        initData()
        initListener()
    }

    abstract fun initViewModel(viewModel: VM)

    /**override it and inflate your view binding, demo in MainActivity*/
    abstract fun inflateViewBinding(inflater: LayoutInflater): VB
    abstract fun initView()
    abstract fun initData()
    abstract fun initListener()

    fun getPermission(): Array<String> {
        return arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

}