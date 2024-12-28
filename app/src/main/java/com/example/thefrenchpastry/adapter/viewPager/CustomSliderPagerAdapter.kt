package com.example.thefrenchpastry.adapter.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.thefrenchpastry.ui.fragment.MainSliderFragment

class CustomSliderPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val data: ArrayList<String>
) : FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment {

        val count = ArrayList<Int>()
        var num = 0

        data.forEach { _ ->
            count.add(num++)
        }

        return MainSliderFragment(data[position], count,position)
    }

}