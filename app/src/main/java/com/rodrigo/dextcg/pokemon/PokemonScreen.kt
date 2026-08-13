package com.rodrigo.dextcg.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PokemonScreen(
    pokemonListState: PokemonListState
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { inner ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().padding(inner)
        ){
            when(pokemonListState){
                is PokemonListState.Idle -> CircularProgressIndicator()
                is PokemonListState.Loading -> CircularProgressIndicator()
                is PokemonListState.Error -> Text("error")
                is PokemonListState.Success -> LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    pokemonListState.pokemonList.forEach { (gen, pokemonList) ->
                        stickyHeader{
                            Text(
                                text = "Generation $gen",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }
                        items(pokemonList) { poke ->
                            Column(modifier = Modifier.fillMaxSize()) {
                                AsyncImage(
                                    model = poke.iconUrl,
                                    contentDescription = poke.displayName,
                                    modifier = Modifier.size(64.dp)
                                )
                                Text(poke.displayName)
                            }
                        }
                    }
                }
            }
        }
    }
}