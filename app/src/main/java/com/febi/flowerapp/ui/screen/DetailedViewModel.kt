package com.febi.flowerapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febi.flowerapp.data.FlowerApi
import com.febi.flowerapp.data.FlowerData
import com.febi.flowerapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val api: FlowerApi): ViewModel() {

    private val _flowerDetailUiState: MutableStateFlow<UiState<FlowerData>> = MutableStateFlow(
        UiState.Loading
    )

    val flowerDetailUiState: StateFlow<UiState<FlowerData>>
        get() = _flowerDetailUiState

    fun getDetailFlower(flowerId: Long) = viewModelScope.launch {
        _flowerDetailUiState.value = UiState.Loading
        _flowerDetailUiState.value = UiState.Success(api.getFlowerById(flowerId))
    }
}