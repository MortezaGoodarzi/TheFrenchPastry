package com.example.thefrenchpastry.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.thefrenchpastry.databinding.CustomVerticalRecyclerBinding

class CustomRecyclerVertical(
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context, attributeSet) {

    private lateinit var binding: CustomVerticalRecyclerBinding

    init {
        binding = CustomVerticalRecyclerBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    fun getRecycler() = binding.recycler

}