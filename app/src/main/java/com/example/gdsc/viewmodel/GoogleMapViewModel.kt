package com.example.gdsc.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GoogleMapViewModel: ViewModel() {

    private var _selectedIndex2 = mutableStateOf(0)

    val selectedIndex2: MutableState<Int> = _selectedIndex2

    var selectedCourse: String by mutableStateOf("")

    var waterQuality: Int by mutableStateOf(0)

    fun onSelectedIndexChange2(index: Int) {
        _selectedIndex2.value = index
        Log.d("ProductDetailViewModel", "onSelectedIndexChange: ${selectedIndex2.value}")
        changeContent()
        waterQuality()
    }

    fun changeContent() {
        when (selectedIndex2.value) {
            1 -> selectedCourse = "Seoul"
            2 -> selectedCourse = "Tokyo"
            3 -> selectedCourse = "Beijing"
            4 -> selectedCourse = "Washington"
        }
    }

    fun waterQuality() {
        when (selectedIndex2.value) {
            1 -> waterQuality = 80
            2 -> waterQuality = 80
            3 -> waterQuality = 20
            4 -> waterQuality = 10
        }
    }
}