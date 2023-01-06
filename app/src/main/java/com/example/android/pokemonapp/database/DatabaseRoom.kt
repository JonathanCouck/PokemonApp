package com.example.android.pokemonapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.pokemonapp.database.pokemon.DatabasePokemon
import com.example.android.pokemonapp.database.pokemon.PokemonDao

@Database(entities = [DatabasePokemon::class], version = 3)
abstract class DatabaseRoom: RoomDatabase() {

    abstract val pokemonDao: PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseRoom? = null

        fun getInstance(context: Context): DatabaseRoom {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseRoom::class.java,
                        "pokemon_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}