package com.example.android.pokemonapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.pokemonapp.database.DatabaseRoom
import com.example.android.pokemonapp.database.pokemon.asDomain
import com.example.android.pokemonapp.domain.PokemonDetail
import com.example.android.pokemonapp.network.pokemon.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

class PokemonRepository(private val db: DatabaseRoom) {

    val pokemon = Transformations.map(db.pokemonDao.getAllPokemon()) {
        it.asDomain()
    }

    suspend fun getPokemonDetails(name: String): LiveData<PokemonDetail?> {
        val result = MutableLiveData<PokemonDetail?>()
        withContext(Dispatchers.IO) {
            try {
                val newPokemon = PokemonApi.retrofitService.getPokemonDetailByNameAsync(name.lowercase()).await()
                newPokemon.let {
                    val abilitiesArray: Array<String> = newPokemon.abilities.map { it.ability.name.replaceFirstChar { // Map abilities.ability.name to abilities capitalized
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    } }.toTypedArray()
                    Log.e("Abilities", abilitiesArray.toString())
                    result.postValue(PokemonDetail(
                        number = newPokemon.number,
                        name = newPokemon.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        },
                        weight = newPokemon.weight,
                        height = newPokemon.height,
                        spriteUrl = newPokemon.sprite.spriteFrontUrl,
                        abilities = abilitiesArray,
                    ))
                    delay(100)
                }
            } catch (e: Exception) {
                Log.e("Can't get detail", "${e.message.toString()}, ${name}")
            }
        }
        return result
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