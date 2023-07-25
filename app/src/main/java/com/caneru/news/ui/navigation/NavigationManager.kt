package com.caneru.news.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.caneru.news.ui.screens.newsbriefs.NewsBriefsScreen
import com.caneru.news.ui.screens.newsdetails.NewsDetailsScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() {

    private lateinit var navController: NavHostController
    private lateinit var startPage: Page
    lateinit var currentPage: MutableState<Page>

    fun setNavigationGraph(navController: NavHostController) {
        this.navController = navController
        startPage = Page("NewsBriefs")
        currentPage = mutableStateOf(startPage)
    }

    @ExperimentalCoroutinesApi
    @Composable
    fun InitializeNavigationGraph() {
        NavHost(navController, startDestination = startPage.route) {
            composable(route = "NewsBriefs") {
                NewsBriefsScreen()
            }
            composable(
                route = "NewsDetails&rssDataID={rssDataID}", arguments = listOf(
                    navArgument("rssDataID") {
                        type = NavType.StringType
                        defaultValue = ""
                    })
            ) {
                NewsDetailsScreen(rssDataID = it.arguments?.getString("rssDataID"))
            }
        }

    }

    fun navigate(route: String) {
        navController.navigate(route)
    }

}