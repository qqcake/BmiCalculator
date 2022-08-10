package com.bigcake.bmicalculator.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.RoundingMode
import kotlin.math.pow

class MainViewModel : ViewModel() {
    private var _weight = MutableStateFlow(50)
    val weight = _weight.asStateFlow()
    private var _height = MutableStateFlow(175)
    val height = _height.asStateFlow()
    private var _bmi = MutableStateFlow(0f)
    val bmi = _bmi.asStateFlow()

    fun onWeightChangedBy(amount: Int) {
        _weight.value += amount
        updateBmi()
    }

    fun onHeightChangedBy(amount: Int) {
        _height.value += amount
        updateBmi()
    }

    private fun updateBmi() {
        val result = (weight.value.toFloat() / height.value.toFloat().div(100).pow(2))
        _bmi.value = result.toBigDecimal().setScale(1, RoundingMode.CEILING).toFloat()
    }
}