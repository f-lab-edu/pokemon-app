package com.sdhong.pokemonapp.view

import coil3.load
import com.sdhong.pokemonapp.base.BaseViewHolder
import com.sdhong.pokemonapp.R
import com.sdhong.pokemonapp.databinding.ItemPokemonSeenBinding
import com.sdhong.pokemonapp.model.Pokemon

class SeenViewHolder(
    private val binding: ItemPokemonSeenBinding,
    private val onClick: (position: Int) -> Unit
) : BaseViewHolder<Pokemon.Seen>(binding.root) {

    init {
        binding.root.setOnClickListener {
            onClick(absoluteAdapterPosition)
        }
    }

    override fun bind(item: Pokemon.Seen) {
        binding.imageViewPokemonSeen.load(item.imgUrl)
        binding.textViewPokemonSeen.text = item.name
        binding.textViewLastViewed.run {
            text = context.getString(R.string.last_viewed, item.lastViewed)
        }
    }
}