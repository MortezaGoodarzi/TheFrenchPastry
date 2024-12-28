package com.example.thefrenchpastry.mvp.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thefrenchpastry.adapter.recycler.NewPastryRecyclerAdapter
import com.example.thefrenchpastry.adapter.recycler.SpecialPastryRecyclerAdapter
import com.example.thefrenchpastry.adapter.recycler.TopPastryRecyclerAdapter
import com.example.thefrenchpastry.androidWrapper.ActivityUtils
import com.example.thefrenchpastry.androidWrapper.PicassoHandler
import com.example.thefrenchpastry.data.remote.dataModel.PastriesModel
import com.example.thefrenchpastry.data.remote.dataModel.RequestMain
import com.example.thefrenchpastry.databinding.HomeFragmentBinding
import com.example.thefrenchpastry.ui.activity.ListPastryActivity

class ViewHomeFragment : FrameLayout {

    private lateinit var activityUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        actUtils: ActivityUtils
    ) : super(contextInstance) {
        activityUtils = actUtils
    }

    val binding = HomeFragmentBinding.inflate(LayoutInflater.from(context))

    fun startGetData() {
        binding.content.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun endGetData() {
        binding.content.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun initialized(data: RequestMain) {

        binding.sliderViewPager.layoutDirection = View.LAYOUT_DIRECTION_RTL
        activityUtils.setViewPagerFragment(binding.sliderViewPager, data.sliders)

        binding.newPastryRecycler.getRecycler().layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
        binding.newPastryRecycler.getRecycler().adapter =
            NewPastryRecyclerAdapter(data.pastries[0].pastries, context)


        binding.specialRecycler.getRecycler().layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)

        val specialOfferData = data.pastries[1].pastries
        specialOfferData.add(
            0,
            PastriesModel(
                0,
                "",
                0,
                "",
                0,
                0,
                false,
                ""
            )
        )
        specialOfferData.add(
            PastriesModel(
                0,
                "",
                0,
                "",
                0,
                0,
                false,
                ""
            )
        )

        binding.specialRecycler.getRecycler().adapter =
            SpecialPastryRecyclerAdapter(specialOfferData, context)

        binding.topPastryRecycler.getRecycler().layoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

        binding.topPastryRecycler.getRecycler().adapter =
            TopPastryRecyclerAdapter(context, data.pastries[2].pastries)

        if (data.banners.isNotEmpty() && data.banners[0].large.isNotEmpty())
            PicassoHandler.setPicasso(binding.imgBanner,data.banners[0].large)

        binding.newPastryRecycler.getAll().setOnClickListener{
            val intent = Intent(context, ListPastryActivity::class.java)
            intent.putExtra(ListPastryActivity.TYPE, ListPastryActivity.NEW)
            context.startActivity(intent)
        }

        binding.topPastryRecycler.getAll().setOnClickListener {
            val intent = Intent(context, ListPastryActivity::class.java)
            intent.putExtra(ListPastryActivity.TYPE, ListPastryActivity.RATE)
            context.startActivity(intent)
        }


    }

}