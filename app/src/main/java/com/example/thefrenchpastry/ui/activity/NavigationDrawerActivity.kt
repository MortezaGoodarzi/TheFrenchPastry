package com.example.thefrenchpastry.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.thefrenchpastry.R
import com.example.thefrenchpastry.databinding.ActivityNavigationDrawerBinding


class NavigationDrawerActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityNavigationDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        useAnim(
            OVERRIDE_TRANSITION_OPEN,
            R.anim.trans_left_in,
            R.anim.trans_left_out
        )


        binding.imgCloseNav.setOnClickListener(this)
        binding.txtOrders.setOnClickListener(this)
        binding.txtExit.setOnClickListener(this)
        binding.txtAboutUs.setOnClickListener(this)
        binding.txtSupport.setOnClickListener(this)
        binding.txtUpgrade.setOnClickListener(this)
        binding.txtContactUs.setOnClickListener(this)

        onBack()

    }


    private fun onBack() {
        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    finish()
                    useAnim(
                        OVERRIDE_TRANSITION_CLOSE,
                        R.anim.trans_right_in,
                        R.anim.trans_right_out
                    )
                }
            }
        )
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.txt_about_us -> {
                finish()
                startActivity(Intent(this, AboutActivity::class.java))

            }

            R.id.txt_contact_us -> {

            }

            R.id.txt_orders -> {

            }

            R.id.txt_support -> {

            }

            R.id.txt_exit -> {
                finishAffinity()
            }

            R.id.txt_upgrade -> {

            }

            R.id.imgCloseNav -> {

                finish()

            }

        }

    }

    private fun useAnim(type: Int, enterAnim: Int, closeAnim: Int) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(
                type,
                enterAnim,
                closeAnim
            )
        } else {
            overridePendingTransition(enterAnim, closeAnim)
        }

    }
}