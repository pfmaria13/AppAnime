package com.example.appanime.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appanime.api.ApiService
import com.example.appanime.models.Anime

@Composable
fun AnimeListScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    var animeList by remember { mutableStateOf<List<Anime>>(emptyList()) }

    LaunchedEffect(Unit) {
        ApiService.getAnimeList { result ->
            animeList = result ?: emptyList()
            Log.d("AnimeListScreen", "Fetched anime list: $animeList")
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text("Anime List")} )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(animeList) { anime ->
                AnimeItem(anime) {
                    Log.d("AnimeListScreen", "Selected anime ID: ${anime.mal_id}") // Логирование ID
                    navController.navigate("animeDetail/${anime.mal_id}")
                }
            }
        }
    }
}

@Composable
fun AnimeItem(anime: Anime, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Text(
            text = anime.title,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h6,
        )
    }
}