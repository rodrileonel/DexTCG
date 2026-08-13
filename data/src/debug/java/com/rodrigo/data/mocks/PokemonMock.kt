package com.rodrigo.data.mocks

import com.rodrigo.data.dto.PokemonDto

object PokemonMock {
    fun bulbasaur() = PokemonDto(
        1,
        "bulbasaur",
        "Bulbasaur",
        1,
        1,
        1,
        "normal",
        "bulbasaur",
        true,
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/1.png",
        1,
        "https://pokeapi.co/api/v2/pokemon/1/",
        1,
        "https://pokeapi.co/api/v2/pokemon-species/1/",
        1
    )
}