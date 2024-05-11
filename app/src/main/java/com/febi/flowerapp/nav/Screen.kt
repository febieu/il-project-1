package com.febi.flowerapp.nav

sealed class Screen (val route: String, val nameScreen: String){
    object FirstScreen: Screen("first_screen", "First")
    object SecondScreen: Screen("second_screen", "Second")
    object DetailedScreen: Screen("first_screen/{flowerId}", "Detailed"){
        fun createRoute(flowerId: Long) = "first_screen/$flowerId"
    }
    object SecondDetailedScreen: Screen("second_detailed_screen/{flowerMonthId}", "Second Detailed"){
        fun createRoute(flowerMonthId: Long) = "second_detailed_screen/$flowerMonthId"
    }
    object AboutScreen: Screen("about_screen", "About")

}