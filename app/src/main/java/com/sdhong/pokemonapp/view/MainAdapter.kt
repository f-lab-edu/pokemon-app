package com.sdhong.pokemonapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.sdhong.pokemonapp.BaseViewHolder
import com.sdhong.pokemonapp.databinding.ItemPokemonBinding
import com.sdhong.pokemonapp.databinding.ItemPokemonSeenBinding
import com.sdhong.pokemonapp.model.Pokemon

class MainAdapter : ListAdapter<Pokemon, BaseViewHolder<ViewBinding, Pokemon>>(
    object : ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.imgUrl == newItem.imgUrl
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
    ): BaseViewHolder<ViewBinding, Pokemon> {
        return when (viewType) {
            Pokemon.TYPE_NORMAL -> NormalViewHolder(
                ItemPokemonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClick
            ) as BaseViewHolder<ViewBinding, Pokemon>

            else -> SeenViewHolder(
                ItemPokemonSeenBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClick
            ) as BaseViewHolder<ViewBinding, Pokemon>
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, Pokemon>, position: Int) {
        holder.bind(getItem(position))
    }
}