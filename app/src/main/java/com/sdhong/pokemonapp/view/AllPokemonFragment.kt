package com.sdhong.pokemonapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sdhong.pokemonapp.base.BaseFragment
import com.sdhong.pokemonapp.databinding.FragmentAllPokemonBinding
import com.sdhong.pokemonapp.viewmodel.AllPokemonViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AllPokemonFragment : BaseFragment<FragmentAllPokemonBinding>(
    bindingFactory = FragmentAllPokemonBinding::inflate
) {
    private val viewModel: AllPokemonViewModel by viewModels()
    private val allPokemonAdapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllPokemon()

        binding?.recyclerViewAllPokemon?.adapter = allPokemonAdapter
        setUpRecyclerView(binding?.recyclerViewAllPokemon)

        allPokemonAdapter.setOnClick(::onPokemonClick)

        setCollectors()
    }

    private fun setCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allPokemon.collectLatest {
                    allPokemonAdapter.submitList(it)
                }
            }
        }
    }

    private fun onPokemonClick(position: Int) {
        viewModel.onPokemonClick(
            position = position,
            addPokemonSeen = ::addPokemonSeen,
            startDetailActivity = ::startDetailActivity
        )
    }
}