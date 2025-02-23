package com.sdhong.pokemonapp.view

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sdhong.pokemonapp.GridSpacingItemDecoration
import com.sdhong.pokemonapp.common.Grid
import com.sdhong.pokemonapp.databinding.FragmentHistoryBinding
import com.sdhong.pokemonapp.model.Pokemon
import com.sdhong.pokemonapp.model.Pokemons

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

        mainAdapter.submitList(Pokemons.seenPokemons.toList())
    }

    private fun onPokemonClick(position: Int) {
        val dateFormat = SimpleDateFormat.getDateTimeInstance()
        val time = dateFormat.format(Calendar.getInstance().time)

        val pokemon = Pokemons.seenPokemons[position]
        Pokemons.seenPokemons.remove(pokemon)
        Pokemons.seenPokemons.add(
            0,
            Pokemon.Seen(
                name = pokemon.name,
                imgUrl = pokemon.imgUrl,
                lastViewed = time
            )
        )

        val context = context
        if (context != null) {
            startActivity(Intent(context, DetailActivity::class.java))
        }
    }
}