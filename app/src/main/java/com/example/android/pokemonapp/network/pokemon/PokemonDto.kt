package com.example.android.pokemonapp.network.pokemon

import com.example.android.pokemonapp.database.pokemon.DatabasePokemon
import com.squareup.moshi.Json

data class PokemonResponse (
    @Json(name = "results")
    val results: List<PokemonDto>
)

data class PokemonDto(
    @Json(name = "id")
    val number: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "sprites.front_default")
    val sprite: PokemonSpriteDto
)

data class PokemonSpriteDto (
    @Json(name = "front_default")
    val spriteUrl: String
)

fun List<PokemonDto>.asDatabase(): Array<DatabasePokemon> {
    return this.map {
        DatabasePokemon(
            number = it.number,
            name = it.name,
            spriteUrl = it.sprite.spriteUrl
        )
    }.toTypedArray()
}