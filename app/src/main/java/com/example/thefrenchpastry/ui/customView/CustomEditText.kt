package com.example.thefrenchpastry.ui.customView

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.thefrenchpastry.R
import com.example.thefrenchpastry.databinding.CustomEditTextBinding

class CustomEditText(
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context, attributeSet) {

    private val binding =
        CustomEditTextBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        initialize(attributeSet)
    }

    private fun initialize(attributeSet: AttributeSet) {

        val typeArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomEditText)

        val hintText = typeArray.getString(R.styleable.CustomEditText_hint_text)

        val type = typeArray.getInt(R.styleable.CustomEditText_type, 1)

        val RtlSupports =
            typeArray.getBoolean(R.styleable.CustomEditText_RtlSupports, false)

        val maxLength = typeArray.getInt(R.styleable.CustomEditText_max, 0)

        val centerGravity =
            typeArray.getBoolean(R.styleable.CustomEditText_centerGravity, false)


        binding.textInputEditText.hint = hintText
        binding.textInputEditText.inputType = type

        if (RtlSupports) {
            binding.textInputLayout.layoutDirection = LAYOUT_DIRECTION_RTL
            binding.textInputEditText.textDirection = TEXT_DIRECTION_RTL
        }
        if (maxLength > 0) {
            binding.textInputEditText.filters = arrayOf(InputFilter.LengthFilter(maxLength))
        }

        if (centerGravity) {
            binding.textInputEditText.gravity = Gravity.CENTER
        }
        typeArray.recycle()
    }

    fun getText(): String {
        return  binding.textInputEditText.text.toString()
    }

    fun setError(error: String?) {
        binding.textInputEditText.error = error
    }
}