package com.sdhong.pokemonapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil3.load
import com.sdhong.pokemonapp.R
import com.sdhong.pokemonapp.databinding.ActivityDetailBinding
import com.sdhong.pokemonapp.viewmodel.DetailViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setCollectors()
    }

    private fun setCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonDetail.collectLatest {
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