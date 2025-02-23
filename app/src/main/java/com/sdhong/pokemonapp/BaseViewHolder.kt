package com.sdhong.pokemonapp

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<VB : ViewBinding, ITEM : Any>(
    binding: VB
) : ViewHolder(binding.root) {

    abstract fun bind(item: ITEM)
}