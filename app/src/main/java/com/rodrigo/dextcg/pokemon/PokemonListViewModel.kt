package com.rodrigo.dextcg.pokemon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigo.domain.model.Pokemon
import com.rodrigo.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    var pokemonListState by mutableStateOf<PokemonListState>(PokemonListState.Idle)
        private set

    var searchPokeState by mutableStateOf("")
        private set

    private var mainPokemonList: List<Pokemon> = emptyList()

    init {
        getPokemonList()
    }

    fun onSearchPokeChange(value:String){
        searchPokeState = value
        val pokeFiltered = if(value.isEmpty()){
            mainPokemonList
        } else{
            mainPokemonList.filter {
                it.displayName.contains(value, ignoreCase = true)
            }
        }
        val group = pokeFiltered.groupBy {
            it.generation
        }
        pokemonListState= PokemonListState.Success(group)
    }

    fun getPokemonList() {
        viewModelScope.launch {
            pokemonListState = PokemonListState.Loading
            getPokemonListUseCase()
                .onSuccess { it ->
                    mainPokemonList = it
                    val group = it.groupBy { it.generation }
                    pokemonListState= PokemonListState.Success(group)
                }
                .onFailure { pokemonListState = PokemonListState.Error(it.message?:"Error") }
        }
    }
}

sealed class PokemonListState{
    object Idle: PokemonListState()
    object Loading: PokemonListState()
    data class Success(val pokemonList: Map<Int, List<Pokemon>>): PokemonListState()
    data class Error(val message: String): PokemonListState()
}