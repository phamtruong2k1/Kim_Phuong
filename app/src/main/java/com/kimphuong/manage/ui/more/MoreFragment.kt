package com.kimphuong.manage.ui.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimphuong.manage.R
import com.kimphuong.manage.databinding.FragmentMoreBinding
import com.kimphuong.manage.utils.openActivity
import com.kimphuong.manage.utils.setOnSafeClick

class MoreFragment : Fragment() {

    private lateinit var binding : FragmentMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        with(binding){
            llBackup.setOnClickListener {
                requireContext().openActivity(SyncDataActivity::class.java)
            }

            llPasscode.setOnClickListener {
                requireContext().openActivity(PasscodeActivity::class.java)
            }
        }
    }
}