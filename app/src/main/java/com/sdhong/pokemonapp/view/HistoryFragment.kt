package com.sdhong.pokemonapp.view

import android.os.Bundle
import android.view.View
import com.sdhong.pokemonapp.base.BaseFragment
import com.sdhong.pokemonapp.databinding.FragmentHistoryBinding
import com.sdhong.pokemonapp.model.Pokemons

class HistoryFragment : BaseFragment<FragmentHistoryBinding>(
    bindingFactory = FragmentHistoryBinding::inflate
) {
    private val historyAdapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.recyclerViewHistory?.adapter = historyAdapter

        setUpRecyclerView(binding?.recyclerViewHistory)

        historyAdapter.setOnClick(::onPokemonClick)
    }

    override fun onResume() {
        super.onResume()
        historyAdapter.submitList(Pokemons.historyPokemons.toList())
    }

    private fun onPokemonClick(position: Int) {
        val pokemon = Pokemons.historyPokemons[position]
        Pokemons.historyPokemons.remove(pokemon)

        addPokemonHistory(pokemon)
        startDetailActivity()
    }
}