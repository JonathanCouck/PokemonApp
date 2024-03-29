package com.example.android.pokemonapp.network.pokemon

import com.example.android.pokemonapp.database.pokemon.DatabasePokemon
import com.squareup.moshi.Json

data class PokemonResponse (
    @Json(name = "results")
    val results: List<PokemonBasicDto>
)

data class PokemonBasicDto(
    @Json(name = "name")
    val name: String,

    @Json(name = "url")
    val url: String
)

data class PokemonDto(
    @Json(name = "id")
    val number: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "height")
    val height: Int,

    @Json(name = "weight")
    val weight: Double,

    @Json(name = "sprites")
    val sprite: PokemonSpriteDto
)

data class PokemonDetailDto(
    @Json(name = "id")
    val number: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "height")
    val height: Int,

    @Json(name = "weight")
    val weight: Double,

    @Json(name = "sprites")
    val sprite: PokemonSpriteDto,

    @Json(name = "abilities")
    val abilities: List<PokemonAbilities>
)

data class PokemonAbilities (
    @Json(name = "ability")
    val ability: Ability
)

data class Ability (
    @Json(name= "name")
    val name: String
)

data class PokemonSpriteDto (
    @Json(name = "front_default")
    val spriteFrontUrl: String,
    @Json(name = "back_default")
    val spriteBackUrl: String?
)

fun List<PokemonDto>.asDatabase(): Array<DatabasePokemon> {
    return this.map {
        DatabasePokemon(
            number = it.number,
            name = it.name,
            weight = it.weight/10,
            height = it.height*10,
            spriteUrl = it.sprite.spriteFrontUrl,
        )
    }.toTypedArray()
}
