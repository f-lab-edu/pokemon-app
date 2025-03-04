package com.sdhong.pokemonapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import coil3.load
import com.sdhong.pokemonapp.R
import com.sdhong.pokemonapp.base.BaseActivity
import com.sdhong.pokemonapp.databinding.ActivityDetailBinding
import com.sdhong.pokemonapp.util.collectLatestStateFlow
import com.sdhong.pokemonapp.viewmodel.DetailViewModel

class DetailActivity : BaseActivity<ActivityDetailBinding>(
    bindingFactory = ActivityDetailBinding::inflate
) {

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCollectors()
    }

    private fun setCollectors() {
        collectLatestStateFlow(viewModel.pokemonDetail) {
            binding.textViewPokemonName.text = it.name
            binding.imageViewPokemon.load(it.imgUrl)
            binding.textViewPokemonDescription.text = getString(
                R.string.pokemon_description,
                it.weight,
                it.height,
                it.types.joinToString(),
                it.abilities.joinToString()
            )
        }
    }

    companion object {
        private const val POKEMON_ID = "POKEMON_ID"

        fun newIntent(context: Context, pokemonId: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(POKEMON_ID, pokemonId)
            return intent
        }
    }
}