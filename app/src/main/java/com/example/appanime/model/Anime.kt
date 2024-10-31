package com.example.appanime.model

data class Anime(
    val id: Int,
    val name: String,
    val genres: String,
    val status: String,
    val studios: String,
    val startDate: Int,
    val endDate: Int,
    val episodes: Int
)