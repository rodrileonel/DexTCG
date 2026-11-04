package com.rodrigo.domain.usecase

import com.rodrigo.domain.model.PokemonCard
import com.rodrigo.domain.model.PokemonSet
import com.rodrigo.domain.repository.Repository
import javax.inject.Inject

class GetCardsBySetUseCase @Inject constructor(private val repo: Repository) {
    suspend operator fun invoke(setId:Int): Result<List<PokemonCard>>{
        return repo.getCardsBySet(setId)
    }
}