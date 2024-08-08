package com.egeninsesi.news.utils

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.egeninsesi.news.R

@Composable
fun MyBottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        BottomNavigationItem(
            icon = { Icon(painterResource(R.drawable.home), contentDescription = null) },
            label = { Text(stringResource(R.string.title_home)) },
            selected = false,
            onClick = { /* Handle navigation */ }
        )
        BottomNavigationItem(
            icon = { Icon(painterResource(R.drawable.newspaper), contentDescription = null) },
            label = { Text(stringResource(R.string.title_contact)) },
            selected = false,
            onClick = { /* Handle navigation */ }
        )
        BottomNavigationItem(
            icon = { Icon(painterResource(R.drawable.ink), contentDescription = null) },
            label = { Text(stringResource(R.string.title_authors)) },
            selected = false,
            onClick = { /* Handle navigation */ }
        )
    }
}