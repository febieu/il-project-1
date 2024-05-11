package com.febi.flowerapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febi.flowerapp.data.FlowerApi
import com.febi.flowerapp.data.FlowerData
import com.febi.flowerapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SecondViewModel(private val api: FlowerApi): ViewModel() {

    private val _flowerUiState: MutableStateFlow<UiState<List<FlowerData>>> = MutableStateFlow(
        UiState.Loading
    )
    val flowerState: StateFlow<UiState<List<FlowerData>>>
        get() = _flowerUiState

    fun getFlowers(){
        viewModelScope.launch {
            api.getAllFlowerList()
                .catch {
                    _flowerUiState.value = UiState.Error(it.message.toString())
                }
                .collect{data ->
                    _flowerUiState.value = UiState.Success(data)
                }
        }
    }
}