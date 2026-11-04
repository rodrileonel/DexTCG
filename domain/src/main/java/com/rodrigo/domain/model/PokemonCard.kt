package com.rodrigo.domain.model

data class PokemonCard (
    val id: Int,
    val name: String,
    val cleanName: String? = null,
    val number: String? = null,
    val rarity: String? = null,
    val tcgplayerId: Long,
    val foilOnly: Long,
    val printing: String? = null,
    val imageUrl: String? = null,
    val setId: Int
)