package com.example.appanime.screens

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import android.util.Log
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import androidx.compose.ui.res.painterResource
import com.example.appanime.R
import com.example.appanime.api.ApiService
import com.example.appanime.models.AnimeDetail

@Composable
fun AnimeDetailScreen(animeId: Int, navController: NavHostController) {
    var anime by remember { mutableStateOf<AnimeDetail?>(null) }

    LaunchedEffect(animeId) {
        ApiService.getAnimeDetails(animeId) { result ->
            if (result != null) {
                anime = result
            }
            if (anime == null) {
                Log.e("AnimeDetailScreen", "Failed to fetch details for anime ID $animeId")
            } else {
                Log.d("AnimeDetailScreen", "Fetched details for anime ID $animeId: $anime")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Карточка аниме", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.icon_back), contentDescription = "Назад",
                            tint = Color.White)
                    }
                }
            )
        }
    ) { paddingValues ->
        anime?.let { animeData ->
            val scrollState = rememberScrollState()
            Column(modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState)){
                Text(text = "Title: ${animeData.title}", style = MaterialTheme.typography.h6)
                Text(text = "Year: ${animeData.year}", style = MaterialTheme.typography.body1)
                Text(text = "Episodes: ${animeData.episodes}", style = MaterialTheme.typography.body1)
                Text(text = "Synopsis: ${animeData.synopsis}", style = MaterialTheme.typography.body2)

                Spacer(modifier = Modifier.height(16.dp))

                animeData.images?.jpg?.image_url.let { imageUrls ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = imageUrls,
                            contentDescription = "Anime Image",
                            modifier = Modifier.size(300.dp),
                        )
                    }
                    Log.d("Way", "URL: $imageUrls")
                } ?: run {
                    Text("No image available", style = MaterialTheme.typography.body2)
                }

                Spacer(modifier = Modifier.height(50.dp))
            }
        } ?: run {
            CircularProgressIndicator(modifier = Modifier.size(100.dp))
        }
    }
}

private fun shareAnime(title: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Check out this anime: $title")
        type = "text/plain"
    }
}