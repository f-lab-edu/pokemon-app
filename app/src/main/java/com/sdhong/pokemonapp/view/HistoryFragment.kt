package com.sdhong.pokemonapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sdhong.pokemonapp.R
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

        setCollectors()

        binding?.buttonEditHistory?.setOnClickListener {
            viewModel.toggleDeleteMode()
        }
    }

    private fun setCollectors() {
        collectLatestStateFlow(viewModel.historyPokemons) {
            historyAdapter.submitList(it)
        }

        collectLatestStateFlow(viewModel.isDeleteMode) { isDeleteMode ->
            binding?.buttonEditHistory?.text = getString(
                if (isDeleteMode) R.string.pokemon_history_button_delete
                else R.string.pokemon_history_button_edit
            )
        }
    }

    private fun onPokemonClick(position: Int) {
        viewModel.onPokemonClick(
            position = position,
            startDetailActivity = ::startDetailActivity
        )
    }

    override fun onPause() {
        viewModel.initHistoryPokemons()
        super.onPause()
    }
}