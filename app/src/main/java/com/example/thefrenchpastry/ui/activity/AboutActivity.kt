package com.example.thefrenchpastry.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thefrenchpastry.R
import com.example.thefrenchpastry.androidWrapper.ActivityUtils
import com.example.thefrenchpastry.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAnim()

        binding.customAppBar.showNawDrawerAndFinnish(this,
            object : ActivityUtils {
                override fun finished() {
                    finish()
                }
            }
        )


        binding.customAppBar.getBackIcon().setOnClickListener {
            finish()
        }
    }

    private fun setAnim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(
                OVERRIDE_TRANSITION_OPEN,
                R.anim.trans_right_out,
                R.anim.trans_right_in
            )
        } else {
            overridePendingTransition(
                R.anim.trans_right_in,
                R.anim.trans_right_out
            )
        }
    }
}