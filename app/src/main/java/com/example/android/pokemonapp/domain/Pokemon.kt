package com.example.android.pokemonapp.domain

data class Pokemon(
    val number: Int,
    val name: String,
    val weight: Double,
    val height: Int,
    val spriteUrl: String,
)

data class PokemonDetail(
    val number: Int,
    val name: String,
    val weight: Double,
    val height: Int,
    val spriteUrl: String,
    val abilities: String
)
