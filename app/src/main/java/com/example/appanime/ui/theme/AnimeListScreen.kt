package com.example.appanime.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appanime.model.Anime
import com.example.appanime.viewmodel.AnimeViewModel

@Composable
fun AnimeListScreen(viewModel: AnimeViewModel, onAnimeClick: (Anime) -> Unit) {
    val anime = viewModel.animeList.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        items(anime.value) { team ->
            AnimeItem(team, onAnimeClick)
        }
    }
}

@Composable
fun AnimeItem(anime: Anime, onClick: (Anime) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(anime) }
            .padding(vertical = 8.dp)
    ) {
        Text(text = anime.name, style = MaterialTheme.typography.h6)
        Text(text = "${anime.startDate}, - ${anime.endDate}", style = MaterialTheme.typography.body2)
    }
}

