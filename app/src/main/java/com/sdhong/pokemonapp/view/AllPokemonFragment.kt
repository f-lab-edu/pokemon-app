package com.sdhong.pokemonapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sdhong.pokemonapp.base.BaseFragment
import com.sdhong.pokemonapp.databinding.FragmentAllPokemonBinding
import com.sdhong.pokemonapp.util.collectFlow
import com.sdhong.pokemonapp.util.collectLatestFlow
import com.sdhong.pokemonapp.viewmodel.AllPokemonViewModel
import com.sdhong.pokemonapp.viewmodel.AllPokemonViewModel.AllPokemonEvent
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
        viewLifecycleOwner.collectLatestFlow(viewModel.allPokemon) {
            allPokemonAdapter.submitList(it)
        }

        viewLifecycleOwner.collectFlow(viewModel.eventFlow) { event ->
            when (event) {
                is AllPokemonEvent.StartDetailActivity -> startActivity(
                    DetailActivity.newIntent(
                        requireContext(),
                        event.detailUrl
                    )
                )
            }
        }
    }

    private fun onPokemonClick(position: Int) {
        viewModel.onPokemonClick(position)
    }
}