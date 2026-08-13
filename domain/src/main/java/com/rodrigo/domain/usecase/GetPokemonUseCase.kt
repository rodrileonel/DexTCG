package com.rodrigo.domain.usecase

import com.rodrigo.domain.model.Pokemon
import com.rodrigo.domain.repository.Repository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val repo: Repository) {
    suspend operator fun invoke(): Result<List<Pokemon>>{
        return repo.getPokemonList()
    }
}