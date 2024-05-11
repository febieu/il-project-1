package com.febi.flowerapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febi.flowerapp.data.FlowerApi
import com.febi.flowerapp.data.FlowerBirthData
import com.febi.flowerapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SecondDetailViewModel(private val api: FlowerApi): ViewModel() {

    private val _flowerMonthUiState: MutableStateFlow<UiState<FlowerBirthData>> = MutableStateFlow(
        UiState.Loading
    )
    val flowerMonthUiState: StateFlow<UiState<FlowerBirthData>>
        get() = _flowerMonthUiState

    fun getDetailFlowerMonth(flowerId: Long) {
        viewModelScope.launch {
            _flowerMonthUiState.value = UiState.Loading
            _flowerMonthUiState.value = UiState.Success(api.getFlowerBirthById(flowerId))
        }
    }
}