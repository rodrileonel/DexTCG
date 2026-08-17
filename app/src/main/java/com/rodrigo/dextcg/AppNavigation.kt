package com.rodrigo.dextcg

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodrigo.dextcg.auth.AuthScreen
import com.rodrigo.dextcg.auth.AuthState
import com.rodrigo.dextcg.auth.AuthViewModel
import com.rodrigo.dextcg.pokemon.PokemonScreen
import com.rodrigo.dextcg.pokemon.PokemonListViewmodel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Auth.route) {
        composable(Screen.PokemonList.route) {
            val vm: PokemonListViewmodel = hiltViewModel()
            PokemonScreen(vm.pokemonListState)
        }
        composable(Screen.Auth.route) {
            val vm: AuthViewModel = hiltViewModel()
            LaunchedEffect(vm.authState) {
                if (vm.authState is AuthState.Success) {
                    navController.navigate(Screen.PokemonList.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            }
            AuthScreen (vm.authState,{ vm.singInWithGoogle() })
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
    object Auth: Screen("auth")
    object Cards: Screen("cards/{from}/{filter}"){
        const val ARG_FROM = "from"
        const val ARG_FILTER = "filter"
        const val FROM_SET = "set"
        const val FROM_POKEMON = "pokemon"
        fun createRouteFromSet(setId:String) = "cards/$FROM_SET/$setId"
        fun createRouteFromPokemon(name:String) = "cards/$FROM_POKEMON/$name"
    }
}