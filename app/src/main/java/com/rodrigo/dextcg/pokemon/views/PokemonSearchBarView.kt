package com.rodrigo.dextcg.pokemon.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonSearchBarView(
    modifier: Modifier,
    searchPokeState: String,
    onSearchQueryChange: (String) -> Unit
){
    OutlinedTextField(
        modifier = modifier
            .padding(8.dp),
        value = searchPokeState,
        onValueChange = onSearchQueryChange,
        placeholder = { Text("Search Pokemon") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        shape = RoundedCornerShape(28.dp),
        singleLine = true
    )
}

