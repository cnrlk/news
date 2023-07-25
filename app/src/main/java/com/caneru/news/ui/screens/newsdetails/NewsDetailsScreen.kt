package com.caneru.news.ui.screens.newsdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caneru.news.ui.AnimatedVisibilityVertical
import com.caneru.news.ui.views.NewsDetailView

@Composable
fun NewsDetailsScreen(rssDataID: String?) {

    val viewModel: NewsDetailsViewModel = hiltViewModel()
    viewModel.rssDataID = rssDataID ?: ""

    AnimatedVisibilityVertical(visible = viewModel.isLoading()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(color = Color.Blue)
        }
    }
    AnimatedVisibilityVertical(visible = viewModel.isIdle()) {
        NewsDetailView(
            modifier = Modifier.padding(16.dp),
            data = viewModel.newsDetail
        )
    }
}