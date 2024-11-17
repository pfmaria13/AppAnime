package com.example.appanime.models

data class Anime(
    val mal_id: Int,
    val title: String,
    val year: String,
    val episodes: Int,
)

data class AnimeResponse(
    val data: List<Anime>
)

data class AnimeDetail(
    val mal_id: Int,
    val title: String?,
    val episodes: Int,
    val synopsis: String?,
    val year: String?,
    val images: Images?
)

data class DetailResponse(
    val data: AnimeDetail
)

data class Images(
    val jpg: Jpg?
)

data class Jpg(
    val image_url: String?
)

data class Aired(
    val from: String?,
    val to: String?
)