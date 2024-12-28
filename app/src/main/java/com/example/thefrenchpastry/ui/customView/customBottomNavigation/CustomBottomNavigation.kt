package com.example.thefrenchpastry.ui.customView.customBottomNavigation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.thefrenchpastry.R
import com.example.thefrenchpastry.databinding.CustomBottomNavigationBinding

class CustomBottomNavigation(
    context: Context,
    attrib: AttributeSet
) : FrameLayout(context, attrib) {

    private val binding : CustomBottomNavigationBinding

    init {
        binding = CustomBottomNavigationBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)


    }

    fun onClickHelper(activeFragment: ActiveFragment) {

        binding.home.setOnClickListener {
            activeHome()
            activeFragment.setFragment(FragmentType.HOME)
        }

        binding.cake.setOnClickListener {
            activeCake()
            activeFragment.setFragment(FragmentType.CAKE)
        }

        binding.pastry.setOnClickListener {
            activePastry()
            activeFragment.setFragment(FragmentType.PASTRY)
        }

        binding.profile.setOnClickListener {
            activeProfile()
            activeFragment.setFragment(FragmentType.PROFILE)
        }

    }

    private fun activeHome() {

        binding.home.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.cake.background = null
        binding.pastry.background = null
        binding.profile.background = null

    }

    private fun activeCake() {

        binding.cake.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.home.background = null
        binding.pastry.background = null
        binding.profile.background = null

    }

    private fun activePastry() {

        binding.pastry.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.home.background = null
        binding.cake.background = null
        binding.profile.background = null

    }

    private fun activeProfile() {

        binding.profile.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.home.background = null
        binding.cake.background = null
        binding.pastry.background = null

    }


}