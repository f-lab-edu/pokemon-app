package com.sdhong.pokemonapp.view

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil3.load
import com.sdhong.pokemonapp.databinding.ItemPokemonBinding
import com.sdhong.pokemonapp.model.Pokemon

class NormalViewHolder(
    private val binding: ItemPokemonBinding,
    private val onPokemonClick: (position: Int) -> Unit
) : ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onPokemonClick(absoluteAdapterPosition)
        }
    }

    fun bind(pokemon: Pokemon.Normal) {
        binding.imageViewPokemon.load(pokemon.imgUrl)
        binding.textViewPokemon.text = pokemon.name
    }
}