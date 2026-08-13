package com.rodrigo.domain.model

data class Pokemon (
    val id: Int,
    val name: String,
    val displayName: String,
    val pokemonId: Int,
    val dexNumber: Int,
    val generation: Int,
    val formType: String,
    val basePokemon: String,
    val isDefault: Boolean,
    val imageUrl: String,
    val iconUrl: String,
    val pokeApiId: Int,
    val pokeApiUrl: String,
    val speciesId: Int,
    val speciesUrl: String,
    val formId: Int
)