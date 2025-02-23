package com.sdhong.pokemonapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sdhong.pokemonapp.databinding.ItemPokemonBinding
import com.sdhong.pokemonapp.databinding.ItemPokemonSeenBinding
import com.sdhong.pokemonapp.model.Pokemon

class MainAdapter : ListAdapter<Pokemon, ViewHolder>(object : ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.imgUrl == newItem.imgUrl
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}) {

    private lateinit var onClick: (position: Int) -> Unit

    fun setOnClick(onClick: (position: Int) -> Unit) {
        this.onClick = onClick
    }

    override fun getItemViewType(position: Int): Int = getItem(position).viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            Pokemon.TYPE_NORMAL -> NormalViewHolder(
                ItemPokemonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClick
            )

            else -> SeenViewHolder(
                ItemPokemonSeenBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onClick
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is NormalViewHolder -> holder.bind(getItem(position) as Pokemon.Normal)
            is SeenViewHolder -> holder.bind(getItem(position) as Pokemon.Seen)
        }
    }
}