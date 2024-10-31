package com.example.appanime.network

import com.example.appanime.model.Anime
import retrofit2.http.GET

interface AnimeService {
    @GET("anime")
    suspend fun getAnime(): List<Anime>
}