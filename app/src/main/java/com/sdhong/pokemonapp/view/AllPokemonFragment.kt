package com.sdhong.pokemonapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sdhong.pokemonapp.base.BaseFragment
import com.sdhong.pokemonapp.databinding.FragmentAllPokemonBinding
import com.sdhong.pokemonapp.util.collectLatestStateFlow
import com.sdhong.pokemonapp.viewmodel.AllPokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllPokemonFragment : BaseFragment<FragmentAllPokemonBinding>(
    bindingFactory = FragmentAllPokemonBinding::inflate
) {
    private val viewModel: AllPokemonViewModel by viewModels()
    private val allPokemonAdapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewAllPokemon.adapter = allPokemonAdapter

        allPokemonAdapter.setOnClick(::onPokemonClick)

        setCollectors()
    }

    private fun setCollectors() {
        viewLifecycleOwner.collectLatestStateFlow(viewModel.allPokemon) {
            allPokemonAdapter.submitList(it)
        }
    }

    private fun onPokemonClick(position: Int) {
        val pokemon = viewModel.allPokemon.value[position]
        viewModel.onPokemonClick(pokemon)
        startDetailActivity(pokemon)
    }
}