package com.chase.codechallenge.navigation

sealed class NavScreen(val route: String) {
    object HomeScreen : NavScreen(NavRoutes.homeScreen)
    object SearchScreen : NavScreen(NavRoutes.searchCityScreen)
}
