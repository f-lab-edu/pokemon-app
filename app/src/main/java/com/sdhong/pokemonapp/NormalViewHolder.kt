package com.sdhong.pokemonapp

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil3.load
import com.sdhong.pokemonapp.databinding.ItemPokemonBinding
import com.sdhong.pokemonapp.model.Pokemon

class NormalViewHolder(
    private val binding: ItemPokemonBinding
) : ViewHolder(binding.root) {

    fun bind(pokemon: Pokemon.Normal) {
        binding.imageViewPokemon.load(pokemon.imgUrl)
        binding.textViewPokemon.text = pokemon.name
    }
}