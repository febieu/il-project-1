package com.febi.flowerapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlowerData(
    val id: Long,
    val image: Int,
    val title: String,
    val description: String,

): Parcelable