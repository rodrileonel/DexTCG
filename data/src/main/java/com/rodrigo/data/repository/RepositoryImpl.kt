package com.rodrigo.data.repository

import com.rodrigo.data.api.Api
import com.rodrigo.data.dto.PokemonDto
import com.rodrigo.domain.model.Pokemon
import com.rodrigo.domain.repository.Repository
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val api: Api): Repository {
    override suspend fun getPokemonList(): Result<List<Pokemon>> {
        return try {
            val res = api.getPokemonList()
            val list = res.map { dto->
                Pokemon(
                    dto.id,
                    dto.name,
                    dto.displayName,
                    dto.pokemonId,
                    dto.dexNumber,
                    dto.generation,
                    dto.formType,
                    dto.basePokemon,
                    dto.isDefault,
                    dto.imageUrl,
                    dto.iconUrl,
                    dto.pokeApiId,
                    dto.pokeApiUrl,
                    dto.speciesId,
                    dto.speciesUrl,
                    dto.formId
                )
            }
            Result.success(list)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}