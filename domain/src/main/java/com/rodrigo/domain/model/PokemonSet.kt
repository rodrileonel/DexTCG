package com.rodrigo.domain.model

data class PokemonSet (
    val id: Int,
    val name: String,
    val shortName: String? = null,
    val series: String? = null,
    val tcgPlayerId: Int,
    val releaseDate: String? = null,
    val cardCount: Int,
    val order: Int,
    val imageTcgPlayerId: String? = null,
    val ptcGoCode: String? = null,
    val image: String,
)