package com.example.dictionary.navigation

sealed class Destination(val route: String) {
    data object Home : Destination("home")
    data object Favorites : Destination("favorites")
    data object History : Destination("history")
    data object Settings : Destination("settings")
    data object WordDetails : Destination("word_details")
}
