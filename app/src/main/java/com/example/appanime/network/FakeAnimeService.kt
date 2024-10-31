package com.example.appanime.network

import com.example.appanime.model.Anime
import kotlinx.coroutines.delay

class FakeAnimeService : AnimeService {
    override suspend fun getAnime(): List<Anime> {
        delay(1000)

        return listOf(
            Anime(id = 1, name = "HUNTER×HUNTER", genres = "Action, adventure, fantasy", status = "Finished", studios = "MADHOUSE", startDate = 2011, endDate = 2014, episodes = 148),
            Anime(id = 2, name = "Golden State Warriors", genres = "San Francisco", status = "GSW", studios = "West", startDate = 41, endDate = 19, episodes = 25),
            Anime(id = 3, name = "Brooklyn Nets", genres = "Brooklyn", status = "BKN", studios = "East", startDate = 41, endDate = 19, episodes = 12),
            Anime(id = 4, name = "Miami Heat", genres = "Miami", status = "MIA", studios = "East", startDate = 41, endDate = 19, episodes = 24)
        )
    }
}