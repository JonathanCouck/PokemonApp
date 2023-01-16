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
import com.example.android.pokemonapp.Utils
import com.example.android.pokemonapp.databinding.FragmentPokemonSearchBinding
import com.example.android.pokemonapp.databinding.LayoutPokemonBinding

class PokemonSearchFragment : Fragment() {
    private lateinit var binding: FragmentPokemonSearchBinding
    private lateinit var viewModel: PokemonSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentPokemonSearchBinding>(
            inflater, R.layout.fragment_pokemon_search, container, false
        )

        val pokemonSearchVM: PokemonSearchViewModel by activityViewModels()
        viewModel = pokemonSearchVM

        setOnClickListeners()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun handleSearchClick() {
        viewModel.fetchPokemon(binding.searchInput.text.toString())

        Utils.hideSoftKeyboard(this@PokemonSearchFragment.requireContext(), binding.searchInput)
    }

    private fun setOnClickListeners() {
        binding.searchPokemonButton.setOnClickListener {
            handleSearchClick()
        }

        val adapter = PokemonAdapter()
        binding.pokemonRecycler.adapter = adapter

        viewModel.pokemon.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

    }
}