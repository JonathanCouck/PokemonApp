package com.example.android.pokemonapp.screens.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.pokemonapp.R
import com.example.android.pokemonapp.databinding.LayoutPokemonBinding
import com.example.android.pokemonapp.domain.Pokemon

class PokemonAdapter(val clickListener: PokemonClickListener): ListAdapter<Pokemon, PokemonAdapter.ViewHolder>(
    PokemonDiffCallback()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: LayoutPokemonBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutPokemonBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

        fun bind(clickListener: PokemonClickListener, item: Pokemon) {
            binding.pokemon = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
            binding.tvNr.text = String.format("%03d", item.number)
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

class PokemonClickListener(val clickListener: (name: String) -> Unit) {
    fun onClick(pokemon: Pokemon) = clickListener(pokemon.name)
}