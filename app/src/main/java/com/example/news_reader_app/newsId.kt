package com.example.news_reader_app
import NewsDetailScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.news_reader_app.ui.theme.NewsReaderTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp() {
    val navController = rememberNavController()
    var isDarkTheme by remember { mutableStateOf(false) } // Track theme state

    NewsReaderTheme(darkTheme = isDarkTheme) { // Apply Theme
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.app_name)) },
                            actions = {
                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = { isDarkTheme = !isDarkTheme }
                        )
                    }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "news_list",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("news_list") {
                    NewsListScreen(navController)
                }
                composable("news_detail/{newsId}") { backStackEntry ->
                    val newsId = backStackEntry.arguments?.getString("newsId")?.toIntOrNull() ?: 0
                    NewsDetailScreen(newsId, navController)
                }
            }
        }
    }
}
