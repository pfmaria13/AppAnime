package com.example.appanime.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appanime.viewmodel.AnimeViewModel
import com.example.appanime.R

@Composable
fun MainScreen(viewModel: AnimeViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "anime", modifier = Modifier.padding(innerPadding)) {
            composable("anime") {
                AnimeListScreen(viewModel = viewModel, onAnimeClick = { anime ->
                    navController.navigate("animeDetail/${anime.id}")
                })
            }
            composable("animeDetail/{animeId}") { backStackEntry ->
                val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull()
                val anime = viewModel.getAnimeById(animeId ?: 0)
                anime?.let {
                    AnimeDetailScreen(anime = it, onBackPress = { navController.popBackStack() })
                }
            }
            composable("home") {
                SettingsScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Аниме", R.drawable.anime_logo, "anime"),
        BottomNavItem("Главная", R.drawable.home_logo, "home")
    )

    BottomNavigation(
        elevation = 8.dp,
        backgroundColor = Color(0xFF6B8E23)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title, tint = Color.White )},
                label = { Text(text = item.title, color = Color.White) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route)
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val title: String, val icon: Int, val route: String)