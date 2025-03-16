package com.sdhong.pokemonapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sdhong.pokemonapp.R
import com.sdhong.pokemonapp.base.BaseFragment
import com.sdhong.pokemonapp.databinding.FragmentHistoryBinding
import com.sdhong.pokemonapp.util.collectFlow
import com.sdhong.pokemonapp.util.collectLatestFlow
import com.sdhong.pokemonapp.viewmodel.HistoryViewModel
import com.sdhong.pokemonapp.viewmodel.HistoryViewModel.HistoryEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>(
    bindingFactory = FragmentHistoryBinding::inflate
) {
    private val viewModel: HistoryViewModel by viewModels()
    private val historyAdapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewHistory.adapter = historyAdapter
        binding.buttonEditHistory.setOnClickListener {
            viewModel.toggleDeleteMode()
        }

        historyAdapter.setOnClick(::onPokemonClick)

        setCollectors()
    }

    private fun setCollectors() {
        viewLifecycleOwner.collectLatestFlow(viewModel.historyPokemons) {
            historyAdapter.submitList(it)
        }

        viewLifecycleOwner.collectLatestFlow(viewModel.isDeleteMode) { isDeleteMode ->
            binding.buttonEditHistory.text = getString(
                if (isDeleteMode) R.string.pokemon_history_button_delete
                else R.string.pokemon_history_button_edit
            )
        }

        viewLifecycleOwner.collectFlow(viewModel.eventFlow) { event ->
            when (event) {
                is HistoryEvent.StartDetailActivity -> startActivity(
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