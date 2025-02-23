package com.sdhong.pokemonapp.view

import coil3.load
import com.sdhong.pokemonapp.BaseViewHolder
import com.sdhong.pokemonapp.databinding.ItemPokemonBinding
import com.sdhong.pokemonapp.model.Pokemon

class NormalViewHolder(
    private val binding: ItemPokemonBinding,
    private val onPokemonClick: (position: Int) -> Unit
) : BaseViewHolder<ItemPokemonBinding, Pokemon.Normal>(binding) {

    init {
        binding.root.setOnClickListener {
            onPokemonClick(absoluteAdapterPosition)
        }
    }

    override fun bind(item: Pokemon.Normal) {
        binding.imageViewPokemon.load(item.imgUrl)
        binding.textViewPokemon.text = item.name
    }
}