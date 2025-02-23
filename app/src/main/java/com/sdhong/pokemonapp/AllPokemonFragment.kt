package com.sdhong.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sdhong.pokemonapp.common.Grid
import com.sdhong.pokemonapp.databinding.FragmentAllPokemonBinding
import com.sdhong.pokemonapp.model.Pokemon
import com.sdhong.pokemonapp.model.allPokemons
import com.sdhong.pokemonapp.model.seenPokemons

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
            layoutManager = GridLayoutManager(context, Grid.DEFAULT_SPAN_COUNT)
            addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount = Grid.DEFAULT_SPAN_COUNT,
                    spacing = Grid.DEFAULT_GRID_SPACING,
                    includeEdge = true
                )
            )
        }

        mainAdapter.submitList(allPokemons)
        mainAdapter.setOnClick(::onPokemonClick)
    }

    fun onPokemonClick(position: Int) {
        val pokemon = allPokemons[position]
        seenPokemons.add(
            0,
            Pokemon.Seen(
                name = pokemon.name,
                imgUrl = pokemon.imgUrl,
                lastViewed = System.currentTimeMillis().toString()
            )
        )

        // TODO: 상세 페이지 이동
    }
}