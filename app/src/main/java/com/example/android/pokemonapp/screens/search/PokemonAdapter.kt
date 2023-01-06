package com.example.android.pokemonapp.screens.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.pokemonapp.databinding.LayoutPokemonBinding
import com.example.android.pokemonapp.domain.Pokemon

class PokemonAdapter: ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(
    PokemonDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: LayoutPokemonBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = LayoutPokemonBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

        fun bind(item: Pokemon) {
            binding.pokemon = item
            binding.executePendingBindings()
        }
    }

    class PokemonDiffCallback: DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

    }
}