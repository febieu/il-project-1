package com.febi.flowerapp.nav

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val imageVector: ImageVector,
    val screen: Screen
)