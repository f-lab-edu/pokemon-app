package com.sdhong.pokemonapp.view

import coil3.load
import com.sdhong.pokemonapp.R
import com.sdhong.pokemonapp.base.BaseViewHolder
import com.sdhong.pokemonapp.databinding.ItemPokemonHistoryBinding
import com.sdhong.pokemonapp.model.Pokemon

class HistoryViewHolder(
    private val binding: ItemPokemonHistoryBinding,
    private val onClick: (position: Int) -> Unit
) : BaseViewHolder<Pokemon.History>(binding.root) {

    init {
        binding.root.setOnClickListener {
            onClick(absoluteAdapterPosition)
        }
    }

    override fun bind(item: Pokemon.History) {
        binding.imageViewPokemonHistory.load(item.imgUrl)
        binding.textViewPokemonHistory.text = item.name
        binding.textViewLastViewed.run {
            text = context.getString(R.string.last_viewed, item.lastViewed)
        }
    }
}