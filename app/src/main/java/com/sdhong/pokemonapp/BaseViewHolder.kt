package com.sdhong.pokemonapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseViewHolder<ITEM : Any>(
    itemView: View
) : ViewHolder(itemView) {

    abstract fun bind(item: ITEM)
}