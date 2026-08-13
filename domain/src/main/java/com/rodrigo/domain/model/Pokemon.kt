package com.rodrigo.domain.model

data class Pokemon (
    val id: Long,
    val name: String,
    val displayName: String,
    val pokemonId: Long,
    val dexNumber: Long,
    val generation: Long,
    val formType: String,
    val basePokemon: String,
    val isDefault: Boolean,
    val imageUrl: String,
    val iconUrl: String,
    val pokeApiId: Long,
    val pokeApiUrl: String,
    val speciesId: Long,
    val speciesUrl: String,
    val formId: Long
)