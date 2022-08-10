package com.bigcake.bmicalculator.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bigcake.bmicalculator.R
import com.bigcake.bmicalculator.databinding.ViewValueCardBinding
import com.bigcake.bmicalculator.ui.main.ValueCardView.OnActionButtonClickedListener.Action
import com.google.android.material.card.MaterialCardView

class ValueCardView : MaterialCardView {
    var onActionButtonClickedListener: OnActionButtonClickedListener? = null
    private var binding: ViewValueCardBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.view_value_card,
        this,
        true
    )

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.ValueCardView, defStyle, 0
        )
        a.getString(R.styleable.ValueCardView_title)?.let {
            binding.title.text = it
        } ?: run {
            binding.title.visibility = GONE
        }
        a.recycle()

        binding.plus.setOnClickListener {
            onActionButtonClicked(it)
        }
        binding.minus.setOnClickListener {
            onActionButtonClicked(it)
        }
    }

    private fun onActionButtonClicked(view: View) {
        onActionButtonClickedListener?.onActionButtonClicked(
            when (view.id) {
                R.id.plus -> Action.PLUS
                R.id.minus -> Action.MINUS
                else -> throw RuntimeException("Unknown view id ${view.id}")
            }
        )
    }

    companion object {
        @BindingAdapter("value")
        @JvmStatic
        fun setValue(view: ValueCardView, value: Int) {
            view.binding.value.text = value.toString()
        }
    }

    interface OnActionButtonClickedListener {
        fun onActionButtonClicked(action: Action)
        enum class Action { PLUS, MINUS }
    }
}