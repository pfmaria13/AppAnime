package com.example.appanime.api

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.appanime.models.Anime
import com.example.appanime.models.AnimeDetail
import com.example.appanime.models.AnimeResponse
import com.example.appanime.models.DetailResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiService {

    private lateinit var client: OkHttpClient

    private const val BASE_URL = "https://api.jikan.moe/v4/"

    private lateinit var retrofit: Retrofit

    private lateinit var api: JikanApi

    fun init(context: Context) {
        val chuckerInterceptor = ChuckerInterceptor.Builder(context)
            .build()

        client = OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(JikanApi::class.java)
    }

    fun getAnimeList(callback: (List<Anime>?) -> Unit) {
        api.getAnimeList().enqueue(object : Callback<AnimeResponse> {
            override fun onResponse(call: Call<AnimeResponse>, response: Response<AnimeResponse>) {
                if (response.isSuccessful) {
                    val animeList = response.body()?.data
                    Log.d("ApiService", "Fetched anime list: $animeList")
                    callback(animeList)
                } else {
                    Log.e("ApiService", "Failed to fetch anime list: ${response.errorBody()?.string()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<AnimeResponse>, t: Throwable) {
                Log.e("ApiService", "Failed to fetch anime list", t)
                callback(null)
            }
        })
    }

    fun getAnimeDetails(animeId: Int, callback: (AnimeDetail?) -> Unit) {
        api.getAnimeDetails(animeId).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                if (response.isSuccessful) {
                    val animeDetail = response.body()?.data
                    Log.d("ApiService", "Fetched anime details: $animeDetail")
                    callback(animeDetail)
                } else {
                    Log.e("ApiService", "Failed to fetch anime details: ${response.errorBody()?.string()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e("ApiService", "Failed to fetch anime details", t)
                callback(null)
            }
        })
    }
}