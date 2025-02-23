package com.sdhong.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sdhong.pokemonapp.databinding.FragmentAllPokemonBinding
import com.sdhong.pokemonapp.model.allPokemons

class AllPokemonFragment : Fragment() {

    private lateinit var binding: FragmentAllPokemonBinding
    private val mainAdapter = MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewAllPokemon.run {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(context, DEFAULT_SPAN_COUNT)
            addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount = DEFAULT_SPAN_COUNT,
                    spacing = DEFAULT_GRID_SPACING,
                    includeEdge = true
                )
            )
        }

        mainAdapter.submitList(allPokemons)
    }

    companion object {
        private const val DEFAULT_SPAN_COUNT = 2
        private const val DEFAULT_GRID_SPACING = 50
    }
}