package com.rodrigo.domain.usecase

import com.rodrigo.domain.model.PokemonSet
import com.rodrigo.domain.repository.Repository
import javax.inject.Inject

class GetPokemonSetsUseCase @Inject constructor(private val repo: Repository) {
    suspend operator fun invoke(): Result<List<PokemonSet>>{
        return repo.getPokemonSets()
    }
}