package com.sdhong.pokemonapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import com.sdhong.pokemonapp.base.BaseViewHolder
import com.sdhong.pokemonapp.databinding.ItemPokemonBinding
import com.sdhong.pokemonapp.databinding.ItemPokemonSeenBinding
import com.sdhong.pokemonapp.model.Pokemon

class MainAdapter : ListAdapter<Pokemon, BaseViewHolder<Pokemon>>(
    object : ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }
) {

    private lateinit var onClick: (position: Int) -> Unit

    fun setOnClick(onClick: (position: Int) -> Unit) {
        this.onClick = onClick
    }

    override fun getItemViewType(position: Int): Int = getItem(position).viewType

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Pokemon> {
        return when (viewType) {
            Pokemon.TYPE_NORMAL -> NormalViewHolder(
                ItemPokemonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClick
            ) as BaseViewHolder<Pokemon>

            else -> SeenViewHolder(
                ItemPokemonSeenBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClick
            ) as BaseViewHolder<Pokemon>
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Pokemon>, position: Int) {
        holder.bind(getItem(position))
    }
}