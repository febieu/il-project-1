package com.febi.flowerapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.febi.flowerapp.nav.NavigationItem
import com.febi.flowerapp.nav.Screen
import com.febi.flowerapp.ui.screen.AboutScreen
import com.febi.flowerapp.ui.screen.DetailedScreen
import com.febi.flowerapp.ui.screen.FirstScreen
import com.febi.flowerapp.ui.screen.SecondDetailedScreen
import com.febi.flowerapp.ui.screen.SecondScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowerApp(
    navController: NavHostController = rememberNavController(),
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val title = when(currentRoute) {
        "first_screen" -> "First"
        "second_screen" -> "Second"
        "about_screen" -> "About"
        else -> ""
    }

    Scaffold(
        bottomBar = {
            if (currentRoute == Screen.DetailedScreen.route) {

            } else {
                BottomAppBar(navController)
            }
        },
//        topBar = {
//
//            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
//                title = {
//                    Text("Small Top App Bar")
//                }
//            )
//        },
//        topBar = {
//            if (currentRoute == Screen.DetailedScreen.route) {
//
//            } else {
//                CenterAlignedTopAppBar(title = {
//                    Text(text = title, color = Color.Black)
//                },
//                    modifier = Modifier.background(Color.Green)
//                )
//
//            }
//        }
//
        topBar = {
            if (currentRoute == Screen.DetailedScreen.route) {

            } else if(currentRoute == Screen.SecondDetailedScreen.route){

            }
            else {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(text = title)
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.FirstScreen.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.FirstScreen.route){
                FirstScreen(
                    onClickDetail = { flowerId ->
                        navController.navigate(Screen.DetailedScreen.createRoute(flowerId))
                    },
                    onClickDetailSecond = {flowerMonthId ->
                    navController.navigate(Screen.SecondDetailedScreen.createRoute(flowerMonthId))
                    }
                )
            }
            composable(Screen.SecondScreen.route) {
                SecondScreen(onClickDetail = {flowerId ->
                    navController.navigate(Screen.DetailedScreen.createRoute(flowerId))
                })
            }
            composable(Screen.DetailedScreen.route,
                arguments = listOf(
                    navArgument("flowerId") {type = NavType.LongType}
                )
            ) {
                val id = it.arguments?.getLong("flowerId") ?: 1
                DetailedScreen(navController = navController, flowerId = id)
                }
            composable(Screen.SecondDetailedScreen.route,
                arguments = listOf(
                    navArgument("flowerMonthId") {type = NavType.LongType}
                )
            ) {
                val id = it.arguments?.getLong("flowerMonthId") ?: 1
                SecondDetailedScreen(navController = navController, flowerMonthId = id)
            }
            composable(Screen.AboutScreen.route) {
                AboutScreen()
            }
        }

    }
}


@Composable
fun BottomAppBar(
    navController: NavHostController,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                imageVector = Icons.Filled.Home,
                screen = Screen.FirstScreen
            ),
            NavigationItem(
                title = "Flower",
                imageVector = Icons.Default.Star,
                screen = Screen.SecondScreen
            ),
            NavigationItem(
                title = "About",
                imageVector = Icons.Default.AccountCircle,
                screen = Screen.AboutScreen
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(imageVector = item.imageVector, contentDescription = item.title)
                },
                label = {
                    Text(
                        text = item.title
                    )
                })
        }
    }
}