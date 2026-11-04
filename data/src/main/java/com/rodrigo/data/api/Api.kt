package com.rodrigo.data.api

import com.rodrigo.data.dto.CardDto
import com.rodrigo.data.dto.PokemonDto
import com.rodrigo.data.dto.PokemonSetDto
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("order") order:String = ApiConstants.ORDER,
    ): List<PokemonDto>

    @GET("sets")
    suspend fun getPokemonSetsList(
        @Query("order") order:String = ApiConstants.ORDER_FIELD,
    ): List<PokemonSetDto>

    @GET("cards")
    suspend fun getCardsBySet(
        @Query("setId") setId: String,
        @Query("select") select: String = "*"
    ): List<CardDto>

    @GET("cards")
    suspend fun getCardsByDex(
        @Query("or") names: String,
        @Query("cleanName") exclusions: List<String>,
    ): List<CardDto>
}