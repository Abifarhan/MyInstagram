package com.example.myinstagram.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myinstagram.ui.MainBottomNavigation
import timber.log.Timber

sealed class Screen(val route: String, val title: String) {
    object Feed : Screen("feed", "Feed")
    object Search : Screen("search", "Search")
    object Post : Screen("post", "Post")
    object Profile : Screen("profile", "Profile")
    object Auth : Screen("auth", "Auth")
    object Story : Screen("story", "Story")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstagramNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            // Show bottom navigation only for main screens
            if (currentDestination?.route in listOf("feed", "search", "post", "profile")) {
                MainBottomNavigation(
                    currentDestination = currentDestination,
                    onNavigateToDestination = { route ->
                        navController.navigate(route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Feed.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Feed.route) {
                Timber.d("Navigating to Feed")
                FeedScreenPlaceholder()
            }

            composable(Screen.Search.route) {
                Timber.d("Navigating to Search")
                SearchScreenPlaceholder()
            }

            composable(Screen.Post.route) {
                Timber.d("Navigating to Post")
                PostScreenPlaceholder()
            }

            composable(Screen.Profile.route) {
                Timber.d("Navigating to Profile")
                ProfileScreenPlaceholder()
            }

            composable(Screen.Auth.route) {
                Timber.d("Navigating to Auth")
                AuthScreenPlaceholder()
            }

            composable(Screen.Story.route) {
                Timber.d("Navigating to Story")
                StoryScreenPlaceholder()
            }
        }
    }
}

// Placeholder screens - these will be replaced with actual feature screens
@Composable
fun FeedScreenPlaceholder() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Feed Screen - Ready for implementation")
    }
}

@Composable
fun SearchScreenPlaceholder() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Search Screen - Ready for implementation")
    }
}

@Composable
fun PostScreenPlaceholder() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Post Screen - Ready for implementation")
    }
}

@Composable
fun ProfileScreenPlaceholder() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Profile Screen - Ready for implementation")
    }
}

@Composable
fun AuthScreenPlaceholder() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Auth Screen - Ready for implementation")
    }
}

@Composable
fun StoryScreenPlaceholder() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Story Screen - Ready for implementation")
    }
}
