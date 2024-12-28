package com.example.thefrenchpastry.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.thefrenchpastry.R
import com.example.thefrenchpastry.databinding.CustomHorizontalRecyclerBinding

class CustomRecyclerHorizontal
    (
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context,attributeSet) {

    private val binding: CustomHorizontalRecyclerBinding

    init {
        binding = CustomHorizontalRecyclerBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
        initialize(attributeSet,context)
    }

    private fun initialize(attr: AttributeSet, context: Context) {

        val typeArray = context.obtainStyledAttributes(attr, R.styleable.CustomRecyclerHorizontal)

        val text = typeArray.getString(R.styleable.CustomRecyclerHorizontal_android_title)
        binding.txtRecycleHor.text = text

        typeArray.recycle()
    }
    fun getRecycler() = binding.recycler
    fun getAll() = binding.more


}