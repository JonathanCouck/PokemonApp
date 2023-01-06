package com.example.android.pokemonapp.screens.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.pokemonapp.domain.Pokemon

class PokemonSearchViewModel(application: Application): AndroidViewModel(application) {

    //list of all pokemon
    private val _pokemon = MutableLiveData<List<Pokemon>>()
    val pokemon: LiveData<List<Pokemon>>
        get() = _pokemon

    init {
        getPokemon()
    }

    private fun getPokemon() {
        _pokemon.value = listOf(
            Pokemon(105, "marowak", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/105.png"),
            Pokemon(215, "sneasel", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/215.png"),
            Pokemon(45, "vileplume", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/45.png"),
            Pokemon(151, "mew", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/151.png")
        )
    }
}