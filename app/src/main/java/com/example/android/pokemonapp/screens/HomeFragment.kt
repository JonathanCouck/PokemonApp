package com.example.android.pokemonapp.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.android.pokemonapp.R
import com.example.android.pokemonapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    //lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)

        binding.searchButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_homeFragment_to_pokemonSearchFragment)
        }

        return binding.root
    }
}