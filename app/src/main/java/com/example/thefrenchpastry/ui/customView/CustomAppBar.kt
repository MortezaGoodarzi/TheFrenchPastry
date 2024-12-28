package com.example.thefrenchpastry.ui.customView

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.thefrenchpastry.R
import com.example.thefrenchpastry.androidWrapper.ActivityUtils
import com.example.thefrenchpastry.databinding.CustomAppBarBinding
import com.example.thefrenchpastry.mvp.ext.BaseLifecycle
import com.example.thefrenchpastry.ui.activity.NavigationDrawerActivity

class CustomAppBar(
    context: Context,
    attrib: AttributeSet
) : FrameLayout(context, attrib) {

    private val binding = CustomAppBarBinding.inflate(LayoutInflater.from(context))


    init {
        addView(binding.root)
        initialize(context, attrib)
    }

    private fun initialize(context: Context, attrib: AttributeSet) {

        val typeArray = context.obtainStyledAttributes(attrib, R.styleable.CustomAppBar)

        val isBack = typeArray.getBoolean(R.styleable.CustomAppBar_backIcon, false)

        if (isBack) {
            binding.imgBack.visibility = VISIBLE
            binding.imgAlert.visibility = INVISIBLE

        }
        typeArray.recycle()
    }

    fun getBackIcon() = binding.imgBack
    fun getMenu() = binding.imgMenu

    fun showNawDrawer(context: Context) {
        binding.imgMenu.setOnClickListener {
            context.startActivity(
                Intent(context, NavigationDrawerActivity::class.java)
            )
        }
    }

    fun showNawDrawerAndFinnish(context: Context, utils: ActivityUtils) {
        binding.imgMenu.setOnClickListener {
            context.startActivity(
                Intent(context, NavigationDrawerActivity::class.java))
            utils.finished()
        }
    }
}
