package com.rodrigo.dextcg.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                HomeBottomBarView(
                    currentRoute = currentRoute,
                    onTabSelected = onTabSelected
                )
            }
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}
