package com.example.android.pokemonapp.screens.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.pokemonapp.database.DatabaseRoom
import com.example.android.pokemonapp.domain.PokemonDetail
import com.example.android.pokemonapp.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonDetailViewModel(application: Application): AndroidViewModel(application) {

    private val database = DatabaseRoom.getInstance(application.applicationContext)
    private val pokemonRepository = PokemonRepository(database)

    val pokemonDetail: LiveData<PokemonDetail> = pokemonRepository.pokemonDetail

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            pokemonRepository.getPokemonDetails(name)
        }
    }
}