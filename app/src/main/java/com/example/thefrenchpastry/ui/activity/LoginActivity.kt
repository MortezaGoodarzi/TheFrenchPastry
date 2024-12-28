package com.example.thefrenchpastry.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import com.example.thefrenchpastry.R
import com.example.thefrenchpastry.databinding.ActivityLoginBinding
import com.example.thefrenchpastry.mvp.model.ModelLoginActivity
import com.example.thefrenchpastry.mvp.presenter.PresenterLoginActivity
import com.example.thefrenchpastry.mvp.view.ViewLoginActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var presenter: PresenterLoginActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewLoginActivity(this)
        setContentView(view.binding.root)

        hideStatus()

        val model = ModelLoginActivity(this)
        val presenter = PresenterLoginActivity(model,view)
        presenter.onCreate()

        onBack()


    }

    private fun onBack() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    finishAffinity()
                }

            }
        )
    }

    private fun hideStatus() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val attrib = window.attributes

            attrib.layoutInDisplayCutoutMode=
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
        }
    }
}