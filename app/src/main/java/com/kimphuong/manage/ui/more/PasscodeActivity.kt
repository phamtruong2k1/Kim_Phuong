package com.kimphuong.manage.ui.more

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kimphuong.manage.R
import com.kimphuong.manage.databinding.ActivityPasscodeBinding
import com.kimphuong.manage.utils.SharePreferenceUtils
import com.kimphuong.manage.utils.showToast

class PasscodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasscodeBinding

    private var passCode = ""
    private var passCodeConfim = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasscodeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initListener()
    }

    private fun initListener() {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }

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

    private var isConfim = false
    private fun clickNumber(number : Int) {
        if (!isConfim) {
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
                isConfim = true
                passCodeConfim = ""
                showPass(passCodeConfim)
                binding.txtTitle.text = "Confirm passcode"
            }
        } else {
            if (number != -1) {
                if (passCodeConfim.length == 4) {
                    return
                }
                passCodeConfim += number
            } else {
                passCodeConfim = passCodeConfim.substring(0, passCodeConfim.length -1)
            }
            showPass(passCodeConfim)
            if (passCodeConfim.length == 4) {
                if (passCode == passCodeConfim) {
                    SharePreferenceUtils.setPassCode(this, passCode)
                    showToast("Success")
                    onBackPressed()
                } else {
                    showToast("Not same")
                }
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
}