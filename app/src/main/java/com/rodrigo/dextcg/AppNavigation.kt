package com.rodrigo.dextcg

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rodrigo.dextcg.auth.AuthScreen
import com.rodrigo.dextcg.auth.AuthState
import com.rodrigo.dextcg.auth.AuthViewModel
import com.rodrigo.dextcg.auth.UserState
import com.rodrigo.dextcg.cards.CardsScreen
import com.rodrigo.dextcg.cards.CardsViewModel
import com.rodrigo.dextcg.home.HomeScreen
import com.rodrigo.dextcg.dex.PokeDexScreen
import com.rodrigo.dextcg.dex.PokeDexViewModel
import com.rodrigo.dextcg.sets.PokemonSetsScreen
import com.rodrigo.dextcg.sets.PokemonSetsViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val authVm: AuthViewModel = hiltViewModel()
    var startDestination = Screen.PokemonList.route //TODO Screen.Auth.route
    if (authVm.currentUserState is UserState.Success)
        startDestination = Screen.PokemonList.route
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.PokemonList.route) {
            val vm: PokeDexViewModel = hiltViewModel()
            PokeDexScreen(
                authVm.currentUserState,
                vm.pokemonListState,
                vm.searchPokeState,
                vm::onSearchPokeChange,
                onNavigateToCards = { name ->
                    navController.navigate(
                        Screen.Cards.createRouteFromDex(name)
                    )
                }
            )
        }
        composable(Screen.PokemonSets.route) {
            val vm: PokemonSetsViewModel = hiltViewModel()
            PokemonSetsScreen(
                authVm.currentUserState,
                vm.pokemonSetsState,
                vm.searchSetState,
                vm::onSearchSetsChange,
                onNavigateToCards = { setId ->
                    navController.navigate(
                        Screen.Cards.createRouteFromSet(setId)
                    )
                }
            )
        }
        composable(Screen.Auth.route) {
            LaunchedEffect(authVm.authState) {
                if (authVm.authState is AuthState.Success) {
                    navController.navigate(Screen.PokemonList.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            }
            AuthScreen (authVm.authState, authVm::singInWithGoogle)
        }
        composable(Screen.Cards.route) {
            val vm: CardsViewModel = hiltViewModel()
            CardsScreen(
                authVm.currentUserState,
                vm.cardsState,
                "",
                onBack = {navController.popBackStack()},
            )
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = backStackEntry?.destination?.route

    HomeScreen(
        currentRoute = currentRoute,
        onTabSelected = { route ->
            navController.navigate(route) {
                popUpTo(
                    navController.graph.findStartDestination().id
                ) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


sealed class Screen(val route:String){
    object PokemonList: Screen("pokemonList")
    object PokemonSets: Screen("sets")
    object Auth: Screen("auth")
    object Cards: Screen("cards/{from}/{filter}"){
        const val ARG_FROM = "from"
        const val ARG_FILTER = "filter"
        const val FROM_SET = "set"
        const val FROM_DEX = "dex"
        fun createRouteFromSet(setId:Int) = "cards/$FROM_SET/$setId"
        fun createRouteFromDex(name:String) = "cards/$FROM_DEX/$name"
    }
}