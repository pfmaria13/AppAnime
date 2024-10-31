package com.example.appanime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.appanime.ui.theme.MainScreen
import com.example.appanime.ui.theme.AppAnimeTheme
import com.example.appanime.viewmodel.AnimeViewModel

class MainActivity : ComponentActivity() {
    private val animeViewModel: AnimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppAnimeTheme {
                MainScreen(viewModel = animeViewModel)
            }
        }
    }
}