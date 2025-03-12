package com.sdhong.pokemonapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sdhong.pokemonapp.R
import com.sdhong.pokemonapp.base.BaseFragment
import com.sdhong.pokemonapp.databinding.FragmentHistoryBinding
import com.sdhong.pokemonapp.util.collectLatestStateFlow
import com.sdhong.pokemonapp.viewmodel.HistoryViewModel
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
        viewLifecycleOwner.collectLatestStateFlow(viewModel.historyPokemons) {
            historyAdapter.submitList(it)
        }

        viewLifecycleOwner.collectLatestStateFlow(viewModel.isDeleteMode) { isDeleteMode ->
            binding.buttonEditHistory.text = getString(
                if (isDeleteMode) R.string.pokemon_history_button_delete
                else R.string.pokemon_history_button_edit
            )
        }
    }

    private fun onPokemonClick(position: Int) {
        val pokemon = viewModel.historyPokemons.value[position]
        viewModel.onPokemonClick(pokemon)
        if (!viewModel.isDeleteMode.value) {
            startDetailActivity(pokemon)
        }
    }
}