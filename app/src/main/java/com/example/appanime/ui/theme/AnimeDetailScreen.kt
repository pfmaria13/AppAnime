package com.example.appanime.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.appanime.model.Anime
import com.example.appanime.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(anime: Anime, onBackPress: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Карточка аниме",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPress() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_back),
                            contentDescription = "Назад",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6B8E23),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }

    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            val (image, nameText, genresText, statusText, studioText, episodesText, statsCard) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.anime_logo),
                contentDescription = "Anime Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = "Название: ${anime.name}",
                modifier = Modifier.constrainAs(nameText) {
                    top.linkTo(image.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "Жанры: ${anime.genres}",
                modifier = Modifier.constrainAs(genresText) {
                    top.linkTo(nameText.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "Статус: ${anime.status}",
                modifier = Modifier.constrainAs(statusText) {
                    top.linkTo(genresText.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "Студия: ${anime.studios}",
                modifier = Modifier.constrainAs(studioText) {
                    top.linkTo(statusText.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = "Количество серий: ${anime.episodes}",
                modifier = Modifier.constrainAs(episodesText) {
                    top.linkTo(studioText.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(statsCard) {
                        top.linkTo(episodesText.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Год начала создания", fontWeight = FontWeight.Bold)
                            Text(text = anime.startDate.toString())
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Год окончания", fontWeight = FontWeight.Bold)
                            Text(text = anime.endDate.toString())
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AnimeDetailScreenPreview() {
    AnimeDetailScreen(
        anime = Anime(
            id = 1,
            name = "HUNTER×HUNTER",
            genres = "Action, adventure, fantasy",
            status = "Finished",
            studios = "MADHOUSE",
            startDate = 2011,
            endDate = 2014,
            episodes = 148
        ),
        onBackPress = {}
    )
}