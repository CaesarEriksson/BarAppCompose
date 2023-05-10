package kth.compose3.navigation

import kth.compose3.R

sealed class BottomNavItem(var title:String, var icon:Int, var route:String){
    object Home : BottomNavItem("Drinks", R.drawable.baseline_local_bar_24,"home_screen")
    object Favorites: BottomNavItem("My Favorites",R.drawable.baseline_favorite_24,"favorites_screen")
    object AddDrink: BottomNavItem("Suggestion",R.drawable.baseline_add_24,"add_suggestion_screen")
    object Info: BottomNavItem("Drink Details",R.drawable.baseline_local_bar_24,"info/{id}")
}
