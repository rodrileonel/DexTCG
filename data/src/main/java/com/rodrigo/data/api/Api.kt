package com.rodrigo.data.api

import com.rodrigo.data.dto.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("order") order:String = ApiConstants.ORDER,
    ): List<PokemonDto>
}