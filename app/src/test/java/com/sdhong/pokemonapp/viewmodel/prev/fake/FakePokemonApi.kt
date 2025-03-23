package com.sdhong.pokemonapp.viewmodel.prev.fake

import com.sdhong.pokemonapp.remote.model.PokemonDetailResponse
import com.sdhong.pokemonapp.remote.model.PokemonListResponse

class FakePokemonApi {

    private val pokemonDetailList = listOf(
        PokemonDetailResponse(
            name = "bulbasaur",
            weight = 69,
            height = 7,
            types = listOf(
                PokemonDetailResponse.Type(
                    type = PokemonDetailResponse.TypeInfo(
                        name = "grass"
                    )
                ),
                PokemonDetailResponse.Type(
                    type = PokemonDetailResponse.TypeInfo(
                        name = "poison"
                    )
                )
            ),
            abilities = listOf(
                PokemonDetailResponse.Ability(
                    ability = PokemonDetailResponse.AbilityInfo(
                        name = "overgrow"
                    )
                ),
                PokemonDetailResponse.Ability(
                    ability = PokemonDetailResponse.AbilityInfo(
                        name = "chlorophyll"
                    )
                )
            ),
            sprites = PokemonDetailResponse.Sprites(
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
            )
        ),
        PokemonDetailResponse(
            name = "ivysaur",
            weight = 130,
            height = 10,
            types = listOf(
                PokemonDetailResponse.Type(
                    type = PokemonDetailResponse.TypeInfo(
                        name = "grass"
                    )
                ),
                PokemonDetailResponse.Type(
                    type = PokemonDetailResponse.TypeInfo(
                        name = "poison"
                    )
                )
            ),
            abilities = listOf(
                PokemonDetailResponse.Ability(
                    ability = PokemonDetailResponse.AbilityInfo(
                        name = "overgrow"
                    )
                ),
                PokemonDetailResponse.Ability(
                    ability = PokemonDetailResponse.AbilityInfo(
                        name = "chlorophyll"
                    )
                )
            ),
            sprites = PokemonDetailResponse.Sprites(
                imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"
            )
        ),
    )

    fun getAllPokemon(): PokemonListResponse {
        return PokemonListResponse(
            next = "",
            results = listOf(
                PokemonListResponse.PokemonListItem(
                    name = "bulbasaur",
                    url = "https://pokeapi.co/api/v2/pokemon/1/"
                ),
                PokemonListResponse.PokemonListItem(
                    name = "ivysaur",
                    url = "https://pokeapi.co/api/v2/pokemon/2/"
                )
            )
        )
    }

    fun getImgUrl(id: Int): String = pokemonDetailList[id - 1].sprites.imgUrl

    fun getPokemonDetail(id: Int): PokemonDetailResponse = pokemonDetailList[id - 1]
}