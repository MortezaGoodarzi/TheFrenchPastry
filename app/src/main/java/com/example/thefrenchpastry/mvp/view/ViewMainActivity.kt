package com.example.thefrenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.thefrenchpastry.androidWrapper.ActivityUtils
import com.example.thefrenchpastry.databinding.ActivityMainBinding
import com.example.thefrenchpastry.ui.customView.customBottomNavigation.ActiveFragment
import com.example.thefrenchpastry.ui.customView.customBottomNavigation.FragmentType
import com.example.thefrenchpastry.ui.fragment.CakeFragment
import com.example.thefrenchpastry.ui.fragment.HomeFragment
import com.example.thefrenchpastry.ui.fragment.PastryFragment
import com.example.thefrenchpastry.ui.fragment.ProfileFragment

class ViewMainActivity : FrameLayout, ActiveFragment {

    private lateinit var activityUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        actUtils: ActivityUtils
    ) : super(contextInstance) {
        activityUtils = actUtils
    }

    val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))

    fun initBottomNav() {
        binding.bottomNav.onClickHelper(this)
    }


    fun initialize() {
        activityUtils.setFragment(HomeFragment(context,activityUtils))
    }

    override fun setFragment(type: FragmentType) {
        val fragment = when(type){
            FragmentType.HOME -> HomeFragment(context, activityUtils)
            FragmentType.CAKE -> CakeFragment(context)
            FragmentType.PASTRY -> PastryFragment(context)
            FragmentType.PROFILE -> ProfileFragment(context)
        }
        activityUtils.setFragment(fragment)
    }

    fun showNavDrawer() {
        binding.appBar.showNawDrawer(context)
    }
}