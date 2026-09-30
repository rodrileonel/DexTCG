package com.rodrigo.dextcg.pokemon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rodrigo.dextcg.auth.UserState
import com.rodrigo.dextcg.pokemon.views.PokemonListView
import com.rodrigo.dextcg.pokemon.views.PokemonSearchBarView
import com.rodrigo.dextcg.pokemon.views.UserAvatarView

@Composable
fun PokemonScreen(
    userState: UserState,
    pokemonListState: PokemonListState,
    searchPokeState: String,
    onSearchQueryChange: (String) -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { inner ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
        ){
            when(pokemonListState){
                is PokemonListState.Idle -> CircularProgressIndicator()
                is PokemonListState.Loading -> CircularProgressIndicator()
                is PokemonListState.Error -> Text("error")
                is PokemonListState.Success -> Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        PokemonSearchBarView(
                            modifier = Modifier.weight(1f),
                            searchPokeState,
                            onSearchQueryChange,
                        )
                        when (userState) {
                            is UserState.Success -> UserAvatarView(userState.user.name)
                            else -> Unit
                        }
                    }
                    PokemonListView(pokemonListState)
                }
            }
        }
    }
}
