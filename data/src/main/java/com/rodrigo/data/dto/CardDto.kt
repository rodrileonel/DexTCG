package com.rodrigo.data.dto

data class CardDto (
    val id: Int,
    val name: String,
    val cleanName: String,
    val number: String,
    val rarity: String,
    val tcgplayerId: Long,
    val foilOnly: Long,
    val printing: String,
    val imageUrl: String,
    val setId: Int
)
