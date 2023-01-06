package com.example.android.pokemonapp.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.android.pokemonapp.R
import com.example.android.pokemonapp.databinding.FragmentPokemonSearchBinding

class PokemonSearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPokemonSearchBinding>(
            inflater, R.layout.fragment_pokemon_search, container, false
        )

        val pokemonSearchVM: PokemonSearchViewModel by activityViewModels()

        val adapter = PokemonAdapter()
        binding.pokemonRecycler.adapter = adapter

        pokemonSearchVM.pokemon.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }
}