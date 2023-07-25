package com.caneru.news.ui.screens.newsbriefs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caneru.news.base.models.DatePreference
import com.caneru.news.ui.AnimatedVisibilityVertical
import com.caneru.news.ui.noRippleClickable
import com.caneru.news.ui.views.NewsBriefView
import com.caneru.news.ui.views.SwitchButton
import com.caneru.news.ui.views.SwitchButtonState

@Composable
fun NewsBriefsScreen() {

    val viewModel: NewsBriefsViewModel = hiltViewModel()

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
        Column(Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Descending")
                SwitchButton(
                    state = if (viewModel.dateOrderPreference == DatePreference.DESCENDING) SwitchButtonState.DISABLED else SwitchButtonState.ENABLED,
                    onSwitchStateChanged = { isEnabled ->
                        if (isEnabled) {
                            viewModel.updateDateOrder(DatePreference.ASCENDING)
                        } else {
                            viewModel.updateDateOrder(DatePreference.DESCENDING)
                        }
                    }
                )
                Text(text = "Ascending")
            }
            LazyColumn(
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 8.dp
                )
            ) {
                items(viewModel.newsBriefs) {
                    NewsBriefView(
                        modifier = Modifier
                            .padding(8.dp)
                            .noRippleClickable {
                                viewModel.onNewsBriefItemTapped(it.rssDataID)
                            },
                        data = it
                    )
                }
            }
        }
    }
}