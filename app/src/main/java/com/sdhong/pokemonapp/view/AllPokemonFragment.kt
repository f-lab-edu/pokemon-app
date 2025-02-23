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
import com.sdhong.pokemonapp.databinding.FragmentAllPokemonBinding
import com.sdhong.pokemonapp.model.Pokemon
import com.sdhong.pokemonapp.model.Pokemons

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

        mainAdapter.submitList(Pokemons.allPokemons)
        mainAdapter.setOnClick(::onPokemonClick)
    }

    private fun onPokemonClick(position: Int) {
        val dateFormat = SimpleDateFormat.getDateTimeInstance()
        val time = dateFormat.format(Calendar.getInstance().time)

        val pokemon = Pokemons.allPokemons[position]
        val existed: Pokemon.Seen? = Pokemons.seenPokemons.find { it.imgUrl == pokemon.imgUrl }
        if (existed != null) {
            Pokemons.seenPokemons.remove(existed)
        }
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