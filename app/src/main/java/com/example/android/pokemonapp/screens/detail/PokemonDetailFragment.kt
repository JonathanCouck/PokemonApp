package com.example.android.pokemonapp.screens.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.android.pokemonapp.R
import com.example.android.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.android.pokemonapp.domain.Pokemon
import com.example.android.pokemonapp.screens.search.PokemonSearchViewModel
import java.util.*

class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var viewModel: PokemonSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentPokemonDetailBinding>(
            inflater, R.layout.fragment_pokemon_detail, container, false
        )

        val pokemonSearchVM: PokemonSearchViewModel by activityViewModels()
        viewModel = pokemonSearchVM

        viewModel.selectedPokemon.observe(viewLifecycleOwner, Observer {
            it?.let {
                (activity as AppCompatActivity).supportActionBar?.title = it.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
                binding.selectedPokemon = it
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonSearchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}