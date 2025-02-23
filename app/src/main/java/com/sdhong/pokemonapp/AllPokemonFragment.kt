package com.sdhong.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sdhong.pokemonapp.databinding.FragmentAllPokemonBinding
import com.sdhong.pokemonapp.model.Pokemon
import com.sdhong.pokemonapp.model.allPokemons

class AllPokemonFragment : Fragment() {

    private lateinit var binding: FragmentAllPokemonBinding
    private val mainAdapter = MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewAllPokemon.adapter = mainAdapter
        mainAdapter.submitList(allPokemons)
    }
}