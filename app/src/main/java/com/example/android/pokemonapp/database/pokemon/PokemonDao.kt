package com.example.android.pokemonapp.database.pokemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemon: Array<DatabasePokemon>)

    @Query("SELECT * FROM pokemon_table ORDER BY number ASC")
    fun getAllPokemon(): LiveData<List<DatabasePokemon>>
}