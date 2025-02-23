package com.sdhong.pokemonapp

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil3.load
import com.sdhong.pokemonapp.databinding.ItemPokemonSeenBinding
import com.sdhong.pokemonapp.model.Pokemon

class SeenViewHolder(
    private val binding: ItemPokemonSeenBinding
) : ViewHolder(binding.root) {

    fun bind(pokemon: Pokemon.Seen) {
        binding.imageViewPokemonSeen.load(pokemon.imgUrl)
        binding.textViewPokemonSeen.text = pokemon.name
        binding.textViewLastViewed.text = "Last viewed: ${pokemon.lastViewed}"
    }
}