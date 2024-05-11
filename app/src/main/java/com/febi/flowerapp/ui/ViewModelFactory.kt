package com.febi.flowerapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.febi.flowerapp.data.FlowerApi
import com.febi.flowerapp.ui.screen.DetailViewModel
import com.febi.flowerapp.ui.screen.FirstViewModel
import com.febi.flowerapp.ui.screen.SecondDetailViewModel
import com.febi.flowerapp.ui.screen.SecondViewModel

class ViewModelFactory(val api: FlowerApi): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SecondViewModel::class.java)){
            return SecondViewModel(api) as T
        } else if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(api) as T
        }else if(modelClass.isAssignableFrom(SecondDetailViewModel::class.java)){
                return SecondDetailViewModel(api) as T
        } else if(modelClass.isAssignableFrom(FirstViewModel::class.java)) {
            return FirstViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}