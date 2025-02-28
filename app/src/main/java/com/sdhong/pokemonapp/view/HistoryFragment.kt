package com.sdhong.pokemonapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sdhong.pokemonapp.base.BaseFragment
import com.sdhong.pokemonapp.databinding.FragmentHistoryBinding
import com.sdhong.pokemonapp.util.collectLatestStateFlow
import com.sdhong.pokemonapp.viewmodel.HistoryViewModel

class HistoryFragment : BaseFragment<FragmentHistoryBinding>(
    bindingFactory = FragmentHistoryBinding::inflate
) {
    private val viewModel: HistoryViewModel by viewModels { HistoryViewModel.Factory }
    private val historyAdapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.recyclerViewHistory?.adapter = historyAdapter

        setUpRecyclerView(binding?.recyclerViewHistory)

        historyAdapter.setOnClick(::onPokemonClick)
        historyAdapter.setOnCheckboxClick(::onPokemonClick)

        setCollectors()
    }

    private fun setCollectors() {
        collectLatestStateFlow(viewModel.historyPokemons) {
            historyAdapter.submitList(it)
        }
    }

    private fun onPokemonClick(position: Int) {
        viewModel.onPokemonClick(
            position = position,
            startDetailActivity = ::startDetailActivity
        )
    }
}