package com.sdhong.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sdhong.pokemonapp.databinding.FragmentAllPokemonBinding

class AllPokemonFragment : Fragment() {

    private lateinit var binding: FragmentAllPokemonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }
}