package com.rodrigo.data.repository

import com.rodrigo.data.api.Api
import com.rodrigo.domain.model.Pokemon
import com.rodrigo.domain.model.PokemonSet
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

    override suspend fun getPokemonSets(): Result<List<PokemonSet>> {
        return try {
            val res = api.getPokemonSetsList()
            val list = res.map { dto->
                PokemonSet(
                    dto.id,
                    dto.name,
                    dto.shortName,
                    dto.series,
                    dto.tcgPlayerId,
                    dto.releaseDate,
                    dto.cardCount,
                    dto.order,
                    dto.imageTcgPlayerId,
                    dto.ptcGoCode,
                    dto.image
                )
            }
            Result.success(list)
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}