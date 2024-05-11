package com.febi.flowerapp.data

object Injection {
    fun provideApi(): FlowerApi{
        return FlowerApi.getInstance()
    }
}