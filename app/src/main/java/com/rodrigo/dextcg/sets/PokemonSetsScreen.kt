package com.rodrigo.dextcg.sets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodrigo.dextcg.auth.UserState
import com.rodrigo.dextcg.pokemon.PokemonListState
import com.rodrigo.dextcg.pokemon.views.PokemonItemView
import com.rodrigo.dextcg.pokemon.views.PokemonListView
import com.rodrigo.dextcg.pokemon.views.PokemonSearchBarView
import com.rodrigo.dextcg.sets.views.PokemonSetsItemView
import com.rodrigo.dextcg.ui.components.LoadingView
import com.rodrigo.dextcg.ui.components.UserAvatarView
import kotlin.collections.component1
import kotlin.collections.component2

@Composable
fun PokemonSetsScreen(
    userState: UserState,
    pokemonSetsState: PokemonSetsState
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { inner ->
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize().padding(inner)
        ) {
            when(pokemonSetsState){
                is PokemonSetsState.Idle -> LoadingView()
                is PokemonSetsState.Loading -> LoadingView()
                is PokemonSetsState.Error -> Text("error")
                is PokemonSetsState.Success -> Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(bottom = 100.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        pokemonSetsState.pokemonSets.forEach { (era, pokemonList) ->
                            stickyHeader {
                                Text(
                                    text = era,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colorScheme.surfaceVariant)
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                )
                            }
                            itemsIndexed(pokemonList) { index,set ->
                                PokemonSetsItemView(
                                    set,
                                    modifier = Modifier.padding(
                                        start = if (index % 2 == 0) 16.dp else 0.dp,
                                        end = if (index % 2 == 1) 16.dp else 0.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}