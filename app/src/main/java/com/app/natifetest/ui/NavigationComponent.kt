package com.app.natifetest.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.natifetest.ui.gif_screen.GifScreen
import com.app.natifetest.ui.list_screen.ListScreen
import java.net.URLEncoder

@Composable
fun NavigationComponent() {
    val configuration = LocalConfiguration.current
    val navController = rememberNavController()
    val onBackScreen = { navController.popBackStack() }
    val toGifScreen =
        { title: String, url: String, webp: String ->
            navController.navigate(
                "${Screen.GIF.name}/$title" +
                        "&${URLEncoder.encode(url, "UTF-8")}" +
                        "&${URLEncoder.encode(webp, "UTF-8")}"
            )
        }

    NavHost(
        navController = navController,
        startDestination = Screen.LIST.name,
    ) {
        composable(
            route = Screen.LIST.name,
            enterTransition = { enterTransaction() },
            exitTransition = { exitTransaction() }
        ) {
            ListScreen(
                configuration = configuration,
                toGifScreen = toGifScreen
            )
        }
        composable(
            route = "${Screen.GIF.name}/{title}&{url}&{webp}",
            enterTransition = { enterTransaction() },
            exitTransition = { exitTransaction() },
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("url") { type = NavType.StringType },
                navArgument("webp") { type = NavType.StringType },
            )
        ) {
            val title = it.arguments?.getString("title") ?: ""
            val url = it.arguments?.getString("url") ?: ""
            val webp = it.arguments?.getString("webp") ?: ""
            GifScreen(
                configuration = configuration,
                title = title,
                url = url,
                webp = webp,
                onBackScreen = { onBackScreen() },
            )
        }
    }
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransaction(): EnterTransition? {
    return when (initialState.destination.route) {
        Screen.LIST.name -> {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        }

        else -> null
    }
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransaction(): ExitTransition? {
    return when (targetState.destination.route) {
        Screen.LIST.name -> {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }

        else -> null
    }
}

enum class Screen {
    LIST, GIF
}