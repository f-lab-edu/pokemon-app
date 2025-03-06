package com.sdhong.pokemonapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.sdhong.pokemonapp.local.model.Pokemon
import com.sdhong.pokemonapp.view.DetailActivity

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingFactory: (inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingFactory(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected fun startDetailActivity(pokemon: Pokemon) {
        startActivity(DetailActivity.newIntent(requireContext(), getPokemonId(pokemon.detailUrl)))
    }

    private fun getPokemonId(url: String): Int = url.split("/")[6].toInt()
}