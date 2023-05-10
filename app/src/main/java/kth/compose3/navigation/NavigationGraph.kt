package kth.compose3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kth.compose3.model.duplicatedDrinks
import kth.compose3.model.favoriteDrinks

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = Modifier.semantics { testTagsAsResourceId = true }
    ) {
        composable(route = BottomNavItem.Home.route) {
            HomeScreen(navController, duplicatedDrinks)
        }
        composable(route = BottomNavItem.Favorites.route){
            FavoritesScreen(navController, favoriteDrinks)
        }
        composable(route = BottomNavItem.AddDrink.route) {
            AddDrinkScreen()
        }
        composable(route = BottomNavItem.Info.route) {
            DetailsScreen(navController)
        }
    }
}