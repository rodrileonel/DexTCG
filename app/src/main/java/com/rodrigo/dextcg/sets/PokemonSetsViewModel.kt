package com.rodrigo.dextcg.sets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigo.dextcg.pokemon.PokemonListState
import com.rodrigo.domain.model.Pokemon
import com.rodrigo.domain.model.PokemonSet
import com.rodrigo.domain.usecase.GetPokemonSetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonSetsViewModel @Inject constructor(
    private val getPokemonSetsUseCase: GetPokemonSetsUseCase
): ViewModel(){
    var pokemonSetsState by mutableStateOf<PokemonSetsState>(PokemonSetsState.Idle)
        private set

    private var mainPokemonSets: List<PokemonSet> = emptyList()

    init {
        getPokemonSets()
    }

    fun getPokemonSets() {
        viewModelScope.launch {
            pokemonSetsState = PokemonSetsState.Loading
            getPokemonSetsUseCase()
                .onSuccess { it ->
                    val sets = it.filter { it.series != null && it.ptcGoCode != null && it.shortName != null }
                    mainPokemonSets = sets
                    val group = sets.groupBy { it.series?:"" }
                    pokemonSetsState= PokemonSetsState.Success(group)
                }
                .onFailure { pokemonSetsState = PokemonSetsState.Error(it.message?:"Error") }
        }
    }
}

sealed class PokemonSetsState{
    object Idle: PokemonSetsState()
    object Loading: PokemonSetsState()
    data class Success(val pokemonSets: Map<String,List<PokemonSet>>): PokemonSetsState()
    data class Error(val message: String): PokemonSetsState()
}