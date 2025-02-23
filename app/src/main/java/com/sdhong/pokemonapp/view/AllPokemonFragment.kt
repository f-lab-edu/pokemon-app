package com.sdhong.pokemonapp.view

import android.os.Bundle
import android.view.View
import com.sdhong.pokemonapp.base.BaseFragment
import com.sdhong.pokemonapp.databinding.FragmentAllPokemonBinding
import com.sdhong.pokemonapp.model.Pokemon
import com.sdhong.pokemonapp.model.Pokemons

class AllPokemonFragment : BaseFragment<FragmentAllPokemonBinding>(
    bindingFactory = FragmentAllPokemonBinding::inflate
) {
    private val allPokemonAdapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.recyclerViewAllPokemon?.adapter = allPokemonAdapter
        setUpRecyclerView(binding?.recyclerViewAllPokemon)

        allPokemonAdapter.setOnClick(::onPokemonClick)
        allPokemonAdapter.submitList(Pokemons.allPokemons)
    }

    private fun onPokemonClick(position: Int) {
        val pokemon = Pokemons.allPokemons[position]
        val existed: Pokemon.Seen? = Pokemons.seenPokemons.find { it.imgUrl == pokemon.imgUrl }
        if (existed != null) {
            Pokemons.seenPokemons.remove(existed)
        }

        addPokemonSeen(pokemon)
        startDetailActivity()
    }
}