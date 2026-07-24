// navigation/AppNavigation.kt
package com.example.albumchampions.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.albumchampions.ui.screens.*

// ── Rotas ────────────────────────────────────────────────────────────────────
sealed class Screen(val route: String) {
    object Home       : Screen("home")
    object Favorites  : Screen("favorites") // <--- ROTA NOVA ADICIONADA AQUI
    object TeamAlbum  : Screen("team_album/{teamId}") {
        fun createRoute(teamId: Int) = "team_album/$teamId"
    }
    object TeamDetail : Screen("team_detail/{teamId}") {
        fun createRoute(teamId: Int) = "team_detail/$teamId"
    }
    object Coach      : Screen("coach/{teamId}") {
        fun createRoute(teamId: Int) = "coach/$teamId"
    }
    object Player     : Screen("player/{playerName}") {
        fun createRoute(playerName: String) = "player/$playerName"
    }
}

// ── NavHost principal ────────────────────────────────────────────────────────
@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onTeamClick = { teamId ->
                    navController.navigate(Screen.TeamAlbum.createRoute(teamId))
                },
                onFavoritesClick = {
                    navController.navigate(Screen.Favorites.route)
                }
            )
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(
                onPlayerClick = { playerName -> navController.navigate(Screen.Player.createRoute(playerName)) },
                onCoachClick = { teamId -> navController.navigate(Screen.Coach.createRoute(teamId)) },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.TeamAlbum.route) { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull() ?: return@composable
            TeamAlbumScreen(
                teamId = teamId,
                onShieldClick  = { navController.navigate(Screen.TeamDetail.createRoute(teamId)) },
                onCoachClick   = { navController.navigate(Screen.Coach.createRoute(teamId)) },
                onPlayerClick  = { playerName -> navController.navigate(Screen.Player.createRoute(playerName)) },
                onBackClick    = { navController.popBackStack() }
            )
        }

        composable(Screen.TeamDetail.route) { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull() ?: return@composable
            TeamDetailScreen(
                teamId    = teamId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Coach.route) { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull() ?: return@composable
            CoachScreen(
                teamId    = teamId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Player.route) { backStackEntry ->
            val playerName = backStackEntry.arguments?.getString("playerName") ?: return@composable
            PlayerScreen(
                playerName  = playerName,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}