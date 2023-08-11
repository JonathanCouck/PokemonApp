package com.example.android.pokemonapp.screens.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.pokemonapp.database.DatabaseRoom
import com.example.android.pokemonapp.domain.PokemonDetail
import com.example.android.pokemonapp.network.pokemon.PokemonDetailDto
import com.example.android.pokemonapp.network.pokemon.PokemonDto
import com.example.android.pokemonapp.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonSearchViewModel(application: Application): AndroidViewModel(application) {

    private val database = DatabaseRoom.getInstance(application.applicationContext)
    private val pokemonRepository = PokemonRepository(database)

    val pokemon = pokemonRepository.pokemon

    private val _selectedPokemon = MutableLiveData<PokemonDetail?>()
    val selectedPokemon: LiveData<PokemonDetail?> = _selectedPokemon

    init {
        fetchAllPokemon("", true)
    }

    fun fetchAllPokemon(searchTerm: String, initial: Boolean = false) {
        viewModelScope.launch {
            pokemonRepository.refreshPokemon(searchTerm.lowercase(), initial)
        }
    }

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            val newPokemon = pokemonRepository.getPokemonDetails(name)
            _selectedPokemon.postValue(newPokemon.value)
        }
    }

    fun selectedAbilitiesToString(): String? {
        return _selectedPokemon.value?.abilities?.joinToString(", ")
    }

    private val _navigateToPokemonDetail = MutableLiveData<String>()
    val navigateToPokemonDetail
        get() = _navigateToPokemonDetail

    fun onPokemonClicked(name: String) {
        _navigateToPokemonDetail.value = name
    }
}