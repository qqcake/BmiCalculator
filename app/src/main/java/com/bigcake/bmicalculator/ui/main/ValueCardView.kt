package com.bigcake.bmicalculator.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bigcake.bmicalculator.R
import com.bigcake.bmicalculator.databinding.ViewValueCardBinding

class ValueCardView : CardView {
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
    }

    companion object {
        @BindingAdapter("value")
        @JvmStatic
        fun setValue(view: ValueCardView, value: Int) {
            view.binding.value.text = value.toString()
        }
    }
}