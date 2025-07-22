package io.github.dwivedyaakash.rickandmortyapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.dwivedyaakash.rickandmortyapp.ui.screens.CharacterDetailScreen
import io.github.dwivedyaakash.rickandmortyapp.ui.screens.HomeScreen

enum class Screens {
    HomeScreen,
    CharacterDetailScreen
}

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.HomeScreen.name
    ) {
        composable(
            route = Screens.HomeScreen.name
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screens.CharacterDetailScreen.name + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            CharacterDetailScreen(backStackEntry.arguments?.getInt("id"))
        }
    }
}
