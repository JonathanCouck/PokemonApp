package com.example.android.pokemonapp.repository

import android.util.Log
import androidx.lifecycle.Transformations
import com.example.android.pokemonapp.database.DatabaseRoom
import com.example.android.pokemonapp.database.pokemon.asDomain
import com.example.android.pokemonapp.network.pokemon.PokemonApi
import com.example.android.pokemonapp.network.pokemon.PokemonDto
import com.example.android.pokemonapp.network.pokemon.asDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.min

class PokemonRepository(private val db: DatabaseRoom) {

    val pokemon = Transformations.map(db.pokemonDao.getAllPokemon()) {
        it.asDomain()
    }

    suspend fun refreshPokemon(searchTerm: String, initial: Boolean) {
        withContext(Dispatchers.IO) {
            try {
                if(initial) {
                    if(db.pokemonDao.getAllPokemon().value?.count() == 0) {
                        var names = PokemonApi.retrofitService.getPokemonAsync().await().results

                        val pokemonList: ArrayList<PokemonDto> = arrayListOf();
                        var i = 0

                        while(i<20 && i < names.count()) {
                            pokemonList.add(PokemonApi.retrofitService.getPokemonByNameAsync(names[i].name).await())
                            i++
                        }
                        db.pokemonDao.insertAll(pokemonList.asDatabase())
                    } else {
                        //Empty else
                    }
                } else {
                    var names = PokemonApi.retrofitService.getPokemonAsync().await().results

                    if(searchTerm.isNotBlank())
                        names = names.filter { p -> p.name.contains(searchTerm) }

                    val pokemonList: ArrayList<PokemonDto> = arrayListOf();
                    var i = 0

                    while(i < min(names.count(), 30)) {
                        pokemonList.add(PokemonApi.retrofitService.getPokemonByNameAsync(names[i].name).await())
                        i++
                    }
                    db.pokemonDao.deleteAll()
                    db.pokemonDao.insertAll(pokemonList.asDatabase())
                }
            } catch (e: Exception) {
                Log.e("Can't get pokemon", e.message.toString())
            }
        }
    }
}