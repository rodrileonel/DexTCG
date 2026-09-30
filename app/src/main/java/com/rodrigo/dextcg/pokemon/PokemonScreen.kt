package com.rodrigo.dextcg.pokemon

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import com.rodrigo.dextcg.auth.UserState
import com.rodrigo.dextcg.pokemon.views.LoadingView
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
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
        ){
            when(pokemonListState){
                is PokemonListState.Idle -> LoadingView()
                is PokemonListState.Loading -> LoadingView()
                is PokemonListState.Error -> Text("error")
                is PokemonListState.Success -> Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp,2.dp),
                        verticalAlignment = Alignment.CenterVertically,

                    ){
                        PokemonSearchBarView(
                            modifier = Modifier.weight(1f),
                            searchPokeState,
                            onSearchQueryChange,
                        )
                        when (userState) {
                            is UserState.Success -> UserAvatarView(userState.user.name)
                            else -> UserAvatarView("Ian Valdivia") //TODO unit
                        }
                    }
                    PokemonListView(pokemonListState.pokemonList)
                }
            }
        }
    }
}
