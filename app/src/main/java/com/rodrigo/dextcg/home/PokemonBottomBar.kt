package com.rodrigo.dextcg.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.rodrigo.dextcg.Screen


@Composable
fun PokemonBottomBar(
    currentRoute: String?,
    onTabSelected: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == Screen.PokemonList.route,
            onClick = { onTabSelected(Screen.PokemonList.route) },
            icon = {
                Icon(Icons.Default.Home, contentDescription = "Pokemon")
            },
            label = { Text("Pokédex") }
        )

        NavigationBarItem(
            selected = currentRoute == Screen.PokemonSets.route,
            onClick = { onTabSelected(Screen.PokemonSets.route) },
            icon = {
                Icon(Icons.Default.List, contentDescription = "Pokemon Sets")
            },
            label = { Text("TCG Sets") }
        )
    }
}
