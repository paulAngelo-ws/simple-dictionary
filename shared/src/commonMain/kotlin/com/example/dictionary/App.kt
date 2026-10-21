package com.example.dictionary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dictionary.navigation.Destination
import com.example.dictionary.screens.FavoritesScreen
import com.example.dictionary.screens.HistoryScreen
import com.example.dictionary.screens.HomeScreen
import com.example.dictionary.screens.SettingsScreen
import com.example.dictionary.screens.WordDetailsScreen
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun App() {
    val navController = rememberNavController()

    MaterialTheme {
        Scaffold(
            bottomBar = {
                DictionaryBottomBar(navController)
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Destination.Home.route,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(Destination.Home.route) {
                    HomeScreen()
                }

                composable(Destination.Favorites.route) {
                    FavoritesScreen()
                }

                composable(Destination.History.route) {
                    HistoryScreen()
                }

                composable(Destination.Settings.route) {
                    SettingsScreen()
                }

                composable(Destination.WordDetails.route) {
                    WordDetailsScreen()
                }
            }
        }
    }
}


@Composable
private fun DictionaryBottomBar(
    navController: NavHostController
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    val destinations = listOf(
        Destination.Home,
        Destination.Favorites,
        Destination.History,
        Destination.Settings
    )

    NavigationBar {
        destinations.forEach { destination ->
            NavigationBarItem(
                selected = currentRoute == destination.route,
                onClick = {
                    navController.navigate(destination.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                },
                icon = {},
                label = {
                    Text(destination.route.replaceFirstChar { it.uppercase() })
                }
            )
        }
    }
}

