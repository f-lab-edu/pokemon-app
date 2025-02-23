package com.sdhong.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sdhong.pokemonapp.common.Grid
import com.sdhong.pokemonapp.databinding.FragmentHistoryBinding
import com.sdhong.pokemonapp.model.allPokemons
import com.sdhong.pokemonapp.model.seenPokemons

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val mainAdapter = MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewHistory.run {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(context, Grid.DEFAULT_SPAN_COUNT)
            addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount = Grid.DEFAULT_SPAN_COUNT,
                    spacing = Grid.DEFAULT_GRID_SPACING,
                    includeEdge = true
                )
            )
        }

        mainAdapter.setOnClick(::onPokemonClick)
    }

    override fun onResume() {
        super.onResume()

        mainAdapter.submitList(seenPokemons.toList())
    }

    fun onPokemonClick(position: Int) {
        val pokemon = seenPokemons[position]
        // TODO: 상세 페이지 이동
    }
}