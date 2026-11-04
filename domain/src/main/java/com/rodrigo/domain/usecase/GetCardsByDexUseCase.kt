package com.rodrigo.domain.usecase

import com.rodrigo.domain.model.PokemonCard
import com.rodrigo.domain.repository.Repository
import javax.inject.Inject

class GetCardsByDexUseCase @Inject constructor(private val repo: Repository) {
    suspend operator fun invoke(names:String): Result<List<PokemonCard>>{
        val aliases = mapOf(
            "gmax" to listOf("gmax", "vmax"),
            "alola" to listOf("alolan", "alola"),
            "galar" to listOf("galarian", "galar"),
            "hisui" to listOf("hisuian", "hisui"),
            "paldea" to listOf("paldean", "paldea"),
        )
        val words = names.lowercase().split("-").filter { it.isNotBlank() }
        val variants = words.fold(listOf(emptyList<String>())) { combinations, word ->
            combinations.flatMap { combination ->
                (aliases[word] ?: listOf(word)).map { alias ->
                    combination + alias
                }
            }
        }
        val exclusions = if (words.size == 1) {
            listOf(
                "not.ilike.*mega*",
                "not.ilike.*gmax*",
                "not.ilike.*vmax*",
            )
        } else {
            emptyList()
        }
        val res = variants
            .flatMap { it.permutations() }
            .map { it.joinToString(" ") }
            .distinct()
            .joinToString(
                separator = ",",
                prefix = "(",
                postfix = ")"
            ) { name ->
                "cleanName.ilike.*$name*"
            }
        return repo.getCardsByDex(res,exclusions)
    }

    fun <T> List<T>.permutations(): List<List<T>> {
        if (size <= 1) return listOf(this)
        return flatMap { element ->
            (this - element).permutations().map { permutation ->
                listOf(element) + permutation
            }
        }
    }
}
