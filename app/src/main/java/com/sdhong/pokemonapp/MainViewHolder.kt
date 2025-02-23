package com.sdhong.pokemonapp

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.sdhong.pokemonapp.databinding.ItemPokemonBinding
import com.sdhong.pokemonapp.model.Pokemon

class MainViewHolder(
    private val binding: ItemPokemonBinding
) : ViewHolder(binding.root) {

    fun bind(pokemon: Pokemon) {
        binding.textViewPokemon.text = pokemon.name
        binding.imageViewPokemon.load(pokemon.imgUrl)
    }
}