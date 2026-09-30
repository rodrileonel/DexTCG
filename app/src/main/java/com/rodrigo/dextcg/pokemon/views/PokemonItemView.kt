package com.rodrigo.dextcg.pokemon.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rodrigo.domain.model.Pokemon

@Composable
fun PokemonItemView(poke: Pokemon)  {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = poke.imageUrl,
            contentDescription = poke.displayName,
            modifier = Modifier.size(64.dp)
        )
        Text(
            poke.displayName,
            textAlign = TextAlign.Center
        )
    }
}