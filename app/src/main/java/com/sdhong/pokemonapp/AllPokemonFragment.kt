package com.sdhong.pokemonapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sdhong.pokemonapp.databinding.FragmentAllPokemonBinding
import com.sdhong.pokemonapp.model.Pokemon

class AllPokemonFragment : Fragment() {

    private lateinit var binding: FragmentAllPokemonBinding
    private val mainAdapter = MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allPokemons = listOf(
            Pokemon(
                name = "bulbasaur",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
            ),
            Pokemon(
                name = "ivysaur",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"
            ),
            Pokemon(
                name = "venusaur",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"
            ),
            Pokemon(
                name = "charmander",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
            ),
            Pokemon(
                name = "charmeleon",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png"
            ),
            Pokemon(
                name = "charizard",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png"
            ),
            Pokemon(
                name = "squirtle",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png"
            ),
            Pokemon(
                name = "wartortle",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png"
            ),
            Pokemon(
                name = "blastoise",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png"
            ),
            Pokemon(
                name = "caterpie",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/10.png"
            ),
            Pokemon(
                name = "metapod",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/11.png"
            ),
            Pokemon(
                name = "butterfree",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png"
            ),
            Pokemon(
                name = "weedle",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/13.png"
            ),
            Pokemon(
                name = "kakuna",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/14.png"
            ),
            Pokemon(
                name = "beedrill",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/15.png"
            ),
            Pokemon(
                name = "pidgey",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/16.png"
            ),
            Pokemon(
                name = "pidgeotto",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/17.png"
            ),
            Pokemon(
                name = "pidgeot",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/18.png"
            ),
            Pokemon(
                name = "rattata",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/19.png"
            ),
            Pokemon(
                name = "raticate",
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/20.png"
            )
        )


        binding.recyclerViewAllPokemon.adapter = mainAdapter
        mainAdapter.submitList(allPokemons)
    }
}