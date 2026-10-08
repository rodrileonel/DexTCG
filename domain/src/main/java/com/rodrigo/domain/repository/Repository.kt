package com.rodrigo.domain.repository

import com.rodrigo.domain.model.Pokemon
import com.rodrigo.domain.model.PokemonSet

interface Repository {
    suspend fun getPokemonList(): Result<List<Pokemon>>
    suspend fun getPokemonSets(): Result<List<PokemonSet>>
}
