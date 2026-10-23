package com.rodrigo.dextcg.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodrigo.dextcg.Screen


@Composable
fun HomeScreen(
    currentRoute: String?,
    onTabSelected: (String) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val showBottomBar = currentRoute in listOf(
        Screen.PokemonList.route,
        Screen.PokemonSets.route
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box{
            content(PaddingValues(0.dp))
            if (showBottomBar) {
                HomeBottomBarView(
                    currentRoute = currentRoute,
                    onTabSelected = onTabSelected,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .navigationBarsPadding()
                        .padding(bottom = 12.dp)
                )
            }
        }

    }
}
