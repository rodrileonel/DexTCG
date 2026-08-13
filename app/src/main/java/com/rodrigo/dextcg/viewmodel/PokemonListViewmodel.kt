package com.rodrigo.dextcg.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigo.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewmodel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    fun loadPokemons() {
        viewModelScope.launch {
            val result = getPokemonListUseCase() // Se ejecuta gracias al operator fun invoke
            result.onSuccess { pokemons ->
                // Actualizar estado de UI
            }.onFailure { error ->
                // Manejar error
            }
        }
    }
}