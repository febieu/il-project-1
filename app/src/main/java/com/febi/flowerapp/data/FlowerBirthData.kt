package com.febi.flowerapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlowerBirthData (
    val id : Long,
    val image : Int,
    val title : String,
    val source : String,
    val funfact : String,
    val desc : String,


) : Parcelable