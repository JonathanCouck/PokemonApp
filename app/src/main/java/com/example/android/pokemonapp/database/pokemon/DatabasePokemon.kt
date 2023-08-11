package com.example.android.pokemonapp.database.pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.pokemonapp.domain.Pokemon
import java.util.*

@Entity(tableName = "pokemon_table")
data class DatabasePokemon (
    @PrimaryKey
    var number: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "weight")
    var weight: Double,

    @ColumnInfo(name = "height")
    var height: Int,

    @ColumnInfo(name = "spriteUrl")
    var spriteUrl: String
)

fun List<DatabasePokemon>.asDomain(): List<Pokemon> {
    return map {
        Pokemon(
            number = it.number,
            name = it.name.replace('-', ' ').capitalize(),
            weight = it.weight,
            height = it.height,
            spriteUrl = it.spriteUrl,
        )
    }
}
