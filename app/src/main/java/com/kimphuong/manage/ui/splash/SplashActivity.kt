package com.kimphuong.manage.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.kimphuong.manage.R
import com.kimphuong.manage.base.BaseActivity
import com.kimphuong.manage.databinding.ActivitySplashBinding
import com.kimphuong.manage.db.entity.CategoryEntity
import com.kimphuong.manage.ui.main.MainActivity
import com.kimphuong.manage.utils.SharePreferenceUtils
import com.kimphuong.manage.utils.show
import com.kimphuong.manage.utils.showToast

@SuppressLint("CustomSplashScreen")
class SplashActivity :
    BaseActivity<SplashViewModel, ActivitySplashBinding>(SplashViewModel::class.java) {
    companion object {
        const val TIME_COUNT = 300L
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun initData() {
        if (SharePreferenceUtils.isFirstOpen(this)) {
            initFirstData()
        } else {
            if (SharePreferenceUtils.getPassCode(this) != "") {
                binding.llPasscode.show()
            } else {
                startMain()
            }
        }
    }

    private fun initFirstData() {
        viewModel.addCategory(
            listOf(
                CategoryEntity(1,true, "Salary", R.drawable.ic_cash),
                CategoryEntity(2,true, "Allowance", R.drawable.ic_allowance),
                CategoryEntity(3,true, "Bonus", R.drawable.ic_bonus),
                CategoryEntity(4,true, "Other", R.drawable.ic_other_account),


                CategoryEntity(5,false, "Food", R.drawable.ic_cate_food),
                CategoryEntity(6,false, "Health", R.drawable.ic_cate_health),
                CategoryEntity(7,false, "Transportation", R.drawable.ic_cate_transp),
                CategoryEntity(8,false, "Hobby", R.drawable.ic_cate_bobby),
                CategoryEntity(9,false, "Fashion", R.drawable.ic_cate_fashion),
                CategoryEntity(10,false, "Education", R.drawable.ic_cate_education),
                CategoryEntity(11,false, "Event", R.drawable.ic_cate_event),
                CategoryEntity(12,false, "Beauty care", R.drawable.ic_cate_beauty_care),
                CategoryEntity(13,false, "Daily life", R.drawable.ic_cate_daily_life),
                CategoryEntity(14,false, "Other", R.drawable.ic_other_account)
            )
        )
        SharePreferenceUtils.setFirstOpen(this, false)
        startMain()
    }

    override fun initListener() {
        binding.number1.setOnClickListener { clickNumber(1) }
        binding.number2.setOnClickListener { clickNumber(2) }
        binding.number3.setOnClickListener { clickNumber(3) }
        binding.number4.setOnClickListener { clickNumber(4) }
        binding.number5.setOnClickListener { clickNumber(5) }
        binding.number6.setOnClickListener { clickNumber(6) }
        binding.number7.setOnClickListener { clickNumber(7) }
        binding.number8.setOnClickListener { clickNumber(8) }
        binding.number9.setOnClickListener { clickNumber(9) }
        binding.number0.setOnClickListener { clickNumber(0) }
        binding.imgDelete.setOnClickListener { clickNumber(-1) }
    }

    private fun startMain() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, TIME_COUNT)
    }

    private var passCode = ""
    private fun clickNumber(number : Int) {
            if (number != -1) {
                if (passCode.length == 4) {
                    return
                }
                passCode += number
            } else {
                passCode = passCode.substring(0, passCode.length -1)
            }
            showPass(passCode)
            if (passCode.length == 4) {
                if (passCode == SharePreferenceUtils.getPassCode(this)) {
                    startMain()
                } else {
                    showToast("Passcode passCode")
                    passCode = ""
                    showPass(passCode)
                }
            }
    }

    private fun showPass(passCode: String) {
        when (passCode.length) {
            0 -> {
                binding.passcode1.setImageResource(R.drawable.ic_round_radio_button_unchecked)
                binding.passcode2.setImageResource(R.drawable.ic_round_radio_button_unchecked)
                binding.passcode3.setImageResource(R.drawable.ic_round_radio_button_unchecked)
                binding.passcode4.setImageResource(R.drawable.ic_round_radio_button_unchecked)
            }
            1 -> {
                binding.passcode1.setImageResource(R.drawable.ic_round_radio_button_checked)
                binding.passcode2.setImageResource(R.drawable.ic_round_radio_button_unchecked)
                binding.passcode3.setImageResource(R.drawable.ic_round_radio_button_unchecked)
                binding.passcode4.setImageResource(R.drawable.ic_round_radio_button_unchecked)
            }
            2 -> {
                binding.passcode1.setImageResource(R.drawable.ic_round_radio_button_checked)
                binding.passcode2.setImageResource(R.drawable.ic_round_radio_button_checked)
                binding.passcode3.setImageResource(R.drawable.ic_round_radio_button_unchecked)
                binding.passcode4.setImageResource(R.drawable.ic_round_radio_button_unchecked)
            }
            3 -> {
                binding.passcode1.setImageResource(R.drawable.ic_round_radio_button_checked)
                binding.passcode2.setImageResource(R.drawable.ic_round_radio_button_checked)
                binding.passcode3.setImageResource(R.drawable.ic_round_radio_button_checked)
                binding.passcode4.setImageResource(R.drawable.ic_round_radio_button_unchecked)
            }
            4 -> {
                binding.passcode1.setImageResource(R.drawable.ic_round_radio_button_checked)
                binding.passcode2.setImageResource(R.drawable.ic_round_radio_button_checked)
                binding.passcode3.setImageResource(R.drawable.ic_round_radio_button_checked)
                binding.passcode4.setImageResource(R.drawable.ic_round_radio_button_checked)
            }
        }
    }

    override fun initViewModel(viewModel: SplashViewModel) {

    }
}