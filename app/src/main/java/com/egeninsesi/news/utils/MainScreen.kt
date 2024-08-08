package com.egeninsesi.news.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { MyBottomNavigationBar(navController) }
    ) { padding ->
        NavHost(navController, startDestination = "home", Modifier.padding(padding)) {
            composable("home") { /* Home screen content */ }
            composable("dashboard") { /* Dashboard screen content */ }
            composable("notifications") { /* Notifications screen content */ }
        }
    }
}
