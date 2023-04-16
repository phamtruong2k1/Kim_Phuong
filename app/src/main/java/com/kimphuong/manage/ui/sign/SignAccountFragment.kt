package com.kimphuong.manage.ui.sign

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.databinding.FragmentSignAccountBinding
import com.kimphuong.manage.ui.main.MainActivity
import com.kimphuong.manage.utils.setOnSafeClick


class SignAccountFragment : BaseFragment<SignAccountViewModel, FragmentSignAccountBinding>(
    SignAccountViewModel::class.java
) {
    override fun inflateLayout(inflater: LayoutInflater, container: ViewGroup?): FragmentSignAccountBinding {
        return FragmentSignAccountBinding.inflate(inflater, container, false)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        binding.btnSignGoogle.setOnSafeClick {
            signInGoogle()
        }

        binding.txtGuest.setOnSafeClick {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun signInGoogle() {

    }

    private var isClickBack = false
    fun onBack() {
        if (isClickBack) {
            activity?.finish()
        } else {
            Toast.makeText(
                requireContext(),
                requireContext().getString(R.string.click_back),
                Toast.LENGTH_SHORT
            ).show()
            isClickBack = true
            Handler(Looper.getMainLooper()).postDelayed({
                isClickBack = false
            }, 1000L)
        }
    }
}