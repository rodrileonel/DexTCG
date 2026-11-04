package com.rodrigo.dextcg.cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigo.dextcg.Screen
import com.rodrigo.domain.model.Pokemon
import com.rodrigo.domain.model.PokemonCard
import com.rodrigo.domain.usecase.GetCardsByDexUseCase
import com.rodrigo.domain.usecase.GetCardsBySetUseCase
import com.rodrigo.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val getCardsBySetUseCase: GetCardsBySetUseCase,
    private val getCardsByDexUseCase: GetCardsByDexUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var cardsState by mutableStateOf<CardsState>(CardsState.Idle)
        private set

    private val filter = savedStateHandle[Screen.Cards.ARG_FILTER]?:""

    private val from = savedStateHandle[Screen.Cards.ARG_FROM]?:""

    init {
        when(from){
            "dex" -> getCardsByDex(filter)
            else -> getCardsBySet(filter.toInt())
        }
    }

    private fun getCardsBySet(filter:Int){
        viewModelScope.launch {
            cardsState = CardsState.Loading
            getCardsBySetUseCase(filter)
                .onSuccess { cardsState = CardsState.Success(it) }
                .onFailure { cardsState = CardsState.Error(it.message?:"Error") }
        }
    }

    private fun getCardsByDex(filter: String){
        viewModelScope.launch {
            cardsState = CardsState.Loading
            getCardsByDexUseCase(filter)
                .onSuccess { cardsState = CardsState.Success(it) }
                .onFailure { cardsState = CardsState.Error(it.message?:"Error") }
        }
    }
}

sealed class CardsState{
    object Idle: CardsState()
    object Loading: CardsState()
    data class Success(val cards: List<PokemonCard>): CardsState()
    data class Error(val message: String): CardsState()
}