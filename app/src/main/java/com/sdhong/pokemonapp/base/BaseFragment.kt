package com.sdhong.pokemonapp.base

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sdhong.pokemonapp.GridSpacingItemDecoration
import com.sdhong.pokemonapp.common.Formatter
import com.sdhong.pokemonapp.model.Pokemon
import com.sdhong.pokemonapp.model.Pokemons
import com.sdhong.pokemonapp.view.DetailActivity

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingFactory: (inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) -> VB
) : Fragment() {

    protected var binding: VB? = null

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingFactory(inflater, container, false)
        return binding?.root
    }

    final override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    protected fun setUpRecyclerView(recyclerView: RecyclerView?) {
        recyclerView?.run {
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount = SPAN_COUNT,
                    spacing = GRID_SPACING,
                    includeEdge = true
                )
            )
        }
    }

    protected fun addPokemonSeen(pokemon: Pokemon) {
        Pokemons.seenPokemons.add(
            0,
            Pokemon.Seen(
                name = pokemon.name,
                imgUrl = pokemon.imgUrl,
                lastViewed = Formatter.dateFormat.format(Calendar.getInstance().time)
            )
        )
    }

    protected fun startDetailActivity() {
        val context = context
        if (context != null) {
            startActivity(Intent(context, DetailActivity::class.java))
        }
    }

    companion object {
        private const val SPAN_COUNT = 2
        private const val GRID_SPACING = 50
    }
}