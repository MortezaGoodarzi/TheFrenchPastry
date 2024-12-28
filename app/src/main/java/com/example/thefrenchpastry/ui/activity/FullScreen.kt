package com.example.thefrenchpastry.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.thefrenchpastry.R
import com.example.thefrenchpastry.data.local.preferences.SecureSharePref
import com.example.thefrenchpastry.data.local.preferences.SharedPrefKey

class FullScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        hideStatus()

        val loginStatus = SecureSharePref.getSharedPref(this)

        Handler(Looper.getMainLooper()).postDelayed({

            if (loginStatus.getBoolean(SharedPrefKey.LOGIN_STATE_KEY, false)) {
                startActivity(Intent(this@FullScreen, MainActivity::class.java))
            } else {
                startActivity(Intent(this@FullScreen, LoginActivity::class.java))

            }
        }, 1500)
    }


    private fun hideStatus() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val attrib = window.attributes
            attrib.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}