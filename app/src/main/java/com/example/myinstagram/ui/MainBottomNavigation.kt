package com.example.myinstagram.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon
)

private val bottomNavItems = listOf(
    BottomNavItem(
        route = "feed",
        title = "Feed",
        icon = Icons.Default.Home,
        selectedIcon = Icons.Default.Home
    ),
    BottomNavItem(
        route = "search",
        title = "Search",
        icon = Icons.Default.Search,
        selectedIcon = Icons.Default.Search
    ),
    BottomNavItem(
        route = "post",
        title = "Post",
        icon = Icons.Default.Add,
        selectedIcon = Icons.Default.Add
    ),
    BottomNavItem(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person,
        selectedIcon = Icons.Default.Person
    )
)

@Composable
fun MainBottomNavigation(
    currentDestination: NavDestination?,
    onNavigateToDestination: (String) -> Unit
) {
    NavigationBar {
        bottomNavItems.forEach { item ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = isSelected,
                onClick = { onNavigateToDestination(item.route) }
            )
        }
    }
}
