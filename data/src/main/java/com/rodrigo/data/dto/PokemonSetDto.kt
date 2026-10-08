package com.rodrigo.data.dto

data class PokemonSetDto (
    val id: Int,
    val name: String,
    val shortName: String? = null,
    val series: String? = null,
    val slug: String,
    val tcgPlayerId: Int,
    val releaseDate: String? = null,
    val cardCount: Int,
    val order: Int,
    val imageTcgPlayerId: String? = null,
    val ptcGoCode: String? = null,
    val image: String,
)
