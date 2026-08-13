package com.rodrigo.domain.repository

import com.rodrigo.domain.model.Pokemon

interface Repository {
    suspend fun getPokemonList(): Result<List<Pokemon>>
}