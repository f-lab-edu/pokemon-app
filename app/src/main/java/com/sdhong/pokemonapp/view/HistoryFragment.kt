package com.sdhong.pokemonapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sdhong.pokemonapp.base.BaseFragment
import com.sdhong.pokemonapp.databinding.FragmentHistoryBinding
import com.sdhong.pokemonapp.viewmodel.HistoryViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.historyPokemons.collectLatest {
                    historyAdapter.submitList(it)
                }
            }
        }
    }

    private fun onPokemonClick(position: Int) {
        viewModel.onPokemonClick(
            position = position,
            startDetailActivity = ::startDetailActivity
        )
    }
}