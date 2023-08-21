package com.example.android.pokemonapp.screens.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.android.pokemonapp.R
import com.example.android.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.android.pokemonapp.screens.search.PokemonSearchViewModel
import kotlinx.coroutines.delay

class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var viewModel: PokemonDetailViewModel

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

        var args = PokemonDetailFragmentArgs.fromBundle(requireArguments())

        val pokemonDetailVM: PokemonDetailViewModel by activityViewModels()
        viewModel = pokemonDetailVM
        (activity as AppCompatActivity).supportActionBar?.title = args.pokemonName

        viewModel.pokemonDetail.observe(viewLifecycleOwner, Observer {
            binding.pokemonDetail = it
        })

        viewModel.fetchPokemon(args.pokemonName)

        return binding.root
    }
}