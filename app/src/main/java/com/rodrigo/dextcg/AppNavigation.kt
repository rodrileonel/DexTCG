package com.rodrigo.dextcg

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodrigo.dextcg.pokemon.PokemonScreen
import com.rodrigo.dextcg.pokemon.PokemonListViewmodel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.PokemonList.route) {
        composable(Screen.PokemonList.route) {
            val vm: PokemonListViewmodel = hiltViewModel()
            PokemonScreen(vm.pokemonListState)
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    AppNavHost(navController)
}


sealed class Screen(val route:String){
    object PokemonList: Screen("pokemonList")
    object Cards: Screen("cards/{from}/{filter}"){
        const val ARG_FROM = "from"
        const val ARG_FILTER = "filter"
        const val FROM_SET = "set"
        const val FROM_POKEMON = "pokemon"
        fun createRouteFromSet(setId:String) = "cards/$FROM_SET/$setId"
        fun createRouteFromPokemon(name:String) = "cards/$FROM_POKEMON/$name"
    }
}