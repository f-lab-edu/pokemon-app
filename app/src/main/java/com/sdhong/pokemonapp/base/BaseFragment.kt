package com.sdhong.pokemonapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sdhong.pokemonapp.GridSpacingItemDecoration
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

    protected fun startDetailActivity(pokemonId: Int) {
        val context = context
        if (context != null) {
            startActivity(DetailActivity.newIntent(context, pokemonId))
        }
    }

    companion object {
        private const val SPAN_COUNT = 2
        private const val GRID_SPACING = 50
    }
}