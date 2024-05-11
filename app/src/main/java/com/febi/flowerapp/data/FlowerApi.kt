package com.febi.flowerapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FlowerApi {
    private val allDataFlower = mutableListOf<FlowerData>()
    private val dataFlowerBirth = mutableListOf<FlowerBirthData>()

    init {
        if(allDataFlower.isEmpty()){
            DataSource.flowerList().forEach { flowerData ->
                allDataFlower.add(flowerData)
            }
        }
        if(dataFlowerBirth.isEmpty()){
            DataSource.flowerBirthList().forEach { flowerBirthData ->
                dataFlowerBirth.add(flowerBirthData)
            }
        }
    }

    fun getAllFlowerList(): Flow<List<FlowerData>> {
        return flowOf(allDataFlower)
    }

    fun getFlowerById(flowerId: Long): FlowerData {
        return allDataFlower.first{
            it.id == flowerId
        }
    }

    fun getFlowerBirthList(): Flow<List<FlowerBirthData>> {
        return flowOf(dataFlowerBirth)
    }

    fun getFlowerBirthById(flowerId: Long): FlowerBirthData {
        return dataFlowerBirth.first{
            it.id == flowerId
        }
    }

    companion object{
        @Volatile
        private var instance: FlowerApi? = null

        fun getInstance(): FlowerApi =
            instance ?: synchronized(this){
                FlowerApi().apply {
                    instance = this
                }
            }
    }
}