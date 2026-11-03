package com.rodrigo.dextcg.cards

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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodrigo.dextcg.auth.UserState
import com.rodrigo.dextcg.sets.PokemonSetsState
import com.rodrigo.dextcg.sets.views.PokemonSetsItemView
import com.rodrigo.dextcg.ui.components.DexOutlinedTextField
import com.rodrigo.dextcg.ui.components.LoadingView
import com.rodrigo.dextcg.ui.components.UserAvatarView
import kotlin.collections.component1
import kotlin.collections.component2

@Composable
fun CardsScreen(
    userState: UserState,
    searchCardsState: String,
    onBack: (String) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { inner ->
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize().padding(inner)
        ) {

        }
    }
}