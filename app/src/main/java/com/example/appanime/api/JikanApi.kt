package com.example.appanime.api

import com.example.appanime.models.AnimeResponse
import com.example.appanime.models.DetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface JikanApi {
    @GET("anime")
    fun getAnimeList(): Call<AnimeResponse>

    @GET("anime/{id}")
    fun getAnimeDetails(@Path("id") animeId: Int): Call<DetailResponse>
}