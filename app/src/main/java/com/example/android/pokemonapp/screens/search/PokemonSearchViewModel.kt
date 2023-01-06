package com.example.android.pokemonapp.screens.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.pokemonapp.database.DatabaseRoom
import com.example.android.pokemonapp.domain.Pokemon
import com.example.android.pokemonapp.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonSearchViewModel(application: Application): AndroidViewModel(application) {

    private val database = DatabaseRoom.getInstance(application.applicationContext)
    private val pokemonRepository = PokemonRepository(database)

    val pokemon = pokemonRepository.pokemon

    init {
        fetchPokemon("", true)
    }

    fun fetchPokemon(searchTerm: String, initial: Boolean = false) {
        viewModelScope.launch {
            pokemonRepository.refreshPokemon(searchTerm.lowercase(), initial)
        }
    }
}