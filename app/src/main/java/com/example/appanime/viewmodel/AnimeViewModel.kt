package com.example.appanime.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appanime.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AnimeViewModel : ViewModel() {
    private val _animeList = MutableStateFlow(listOf(
        Anime(id = 1, name = "HUNTER×HUNTER", genres = "Action, adventure, fantasy", status = "Finished", studios = "MADHOUSE", startDate = 2011, endDate = 2014, episodes = 148),
        Anime(id = 2, name = "Kimetsu no Yaiba", genres = "Action, adventure, drama, fantasy, supernatural", status = "Finished", studios = "ufotable", startDate = 2019, endDate = 2019, episodes = 26),
        Anime(id = 3, name = "Jujutsu Kaisen", genres = "Action, drama, supernatural", status = "Finished", studios = "MAPPA", startDate = 2020, endDate = 2021, episodes = 24),
        Anime(id = 4, name = "Chainsaw Man", genres = "Action, drama, horror, supernatural", status = "MIA", studios = "MAPPA", startDate = 2022, endDate = 2022, episodes = 12))
    )

    val animeList: StateFlow<List<Anime>> = _animeList

    fun getAnimeById(id: Int): Anime? {
        return _animeList.value.find { it.id == id }
    }
}