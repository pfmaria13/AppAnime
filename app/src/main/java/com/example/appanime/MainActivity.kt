package com.example.appanime

import BottomNavigationBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController // Импортируем rememberNavController
import com.example.appanime.api.ApiService
import com.example.appanime.screens.AnimeDetailScreen
import com.example.appanime.screens.AnimeListScreen
import com.example.appanime.screens.HomeScreen
import com.example.appanime.ui.theme.AnimeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiService.init(this)
        setContent {
            AnimeAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    MainScreen(navController)
                }
            }
        }
    }

    @Composable
    fun MainScreen(navController: NavHostController) {
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            NavHost(navController, startDestination = "animeList") {
                composable("animeList") { AnimeListScreen(navController) }
                composable("home") { HomeScreen() }
                composable("animeDetail/{animeId}") { backStackEntry ->
                    val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull()
                    animeId?.let { AnimeDetailScreen(it, navController) }
                }
            }
        }
    }
}