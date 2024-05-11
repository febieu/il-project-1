package com.febi.flowerapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febi.flowerapp.data.FlowerApi
import com.febi.flowerapp.data.FlowerBirthData
import com.febi.flowerapp.data.FlowerData
import com.febi.flowerapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FirstViewModel (

    private val api : FlowerApi

) : ViewModel() {

    private val _flowerUiState : MutableStateFlow<UiState<List<FlowerData>>> = MutableStateFlow(
        UiState.Loading
    )
    private val _flowerBirthUiState : MutableStateFlow<UiState<List<FlowerBirthData>>> = MutableStateFlow(
        UiState.Loading
    )

    val flowerUiState : StateFlow<UiState<List<FlowerData>>>
        get() = _flowerUiState

    val flowerBirthUiState : StateFlow<UiState<List<FlowerBirthData>>>
        get() = _flowerBirthUiState

    fun getFlower(){
        viewModelScope.launch {
            api.getAllFlowerList()
                .catch {
                    _flowerUiState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _flowerUiState.value = UiState.Success(it)
                }
        }
    }

    fun getFlowerBirth(){
        viewModelScope.launch {
            api.getFlowerBirthList()
                .catch {
                    _flowerBirthUiState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _flowerBirthUiState.value = UiState.Success(it)
                }
        }
    }
}