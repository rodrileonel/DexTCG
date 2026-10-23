package com.rodrigo.dextcg.sets.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rodrigo.domain.model.PokemonSet

@Composable
fun PokemonSetsItemView (
    set: PokemonSet,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .aspectRatio(2f)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            model = set.image,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentDescription = set.name,
            contentScale = ContentScale.Fit
        )
    }
}