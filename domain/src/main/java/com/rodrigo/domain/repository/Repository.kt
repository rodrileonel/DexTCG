package com.rodrigo.domain.repository

import com.rodrigo.domain.model.Pokemon
import com.rodrigo.domain.model.PokemonCard
import com.rodrigo.domain.model.PokemonSet

interface Repository {
    suspend fun getPokemonList(): Result<List<Pokemon>>
    suspend fun getPokemonSets(): Result<List<PokemonSet>>
    suspend fun getCardsBySet(setId:Int): Result<List<PokemonCard>>
    suspend fun getCardsByDex(names: String, exclusions:List<String>): Result<List<PokemonCard>>
}
