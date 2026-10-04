package com.rodrigo.dextcg.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodrigo.dextcg.auth.UserState
import com.rodrigo.dextcg.ui.components.LoadingView
import com.rodrigo.dextcg.pokemon.views.PokemonListView
import com.rodrigo.dextcg.pokemon.views.PokemonSearchBarView
import com.rodrigo.dextcg.ui.components.UserAvatarView

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
                            .padding(16.dp,2.dp,),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        PokemonSearchBarView(
                            modifier = Modifier.weight(1f),
                            searchPokeState,
                            onSearchQueryChange,
                        )
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "filter")
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
