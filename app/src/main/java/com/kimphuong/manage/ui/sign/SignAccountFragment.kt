package com.kimphuong.manage.ui.sign

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseFragment
import com.kimphuong.manage.databinding.FragmentSignAccountBinding
import com.kimphuong.manage.ui.main.MainActivity
import com.kimphuong.manage.utils.setOnSafeClick


class SignAccountFragment : BaseFragment<SignAccountViewModel, FragmentSignAccountBinding>(
    R.layout.fragment_sign_account,
    SignAccountViewModel::class.java
) {
    override fun init() {
        super.init()
        initListener()
    }

    private fun initListener() {

        binding.txtSignIn.setOnSafeClick {
            findNavController().navigate(R.id.action_signAccountFragment_to_signInFragment)
        }

        binding.txtSignUp.setOnSafeClick {
            findNavController().navigate(R.id.action_signAccountFragment_to_signUpFragment)
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

    private var isClichBack = false
    fun onBack() {
        if (isClichBack) {
            activity?.finish()
        } else {
            Toast.makeText(
                requireContext(),
                requireContext().getString(R.string.click_back),
                Toast.LENGTH_SHORT
            ).show()
            isClichBack = true
            Handler(Looper.getMainLooper()).postDelayed({
                isClichBack = false
            }, 1000L)
        }
    }
}