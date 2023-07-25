package com.caneru.news.ui.screens.newsdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import com.caneru.news.BaseViewModel
import com.caneru.news.base.models.NewsDetail
import com.caneru.news.base.models.Result
import com.caneru.news.base.usecases.FetchNewsDetailsUseCase
import com.caneru.news.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val fetchNewsDetailsUseCase: FetchNewsDetailsUseCase
) : BaseViewModel() {

    var rssDataID by mutableStateOf("")
    var newsDetail by mutableStateOf(NewsDetail("", "", "", "", "", "", ""))

    init {
        snapshotFlow { rssDataID }
            .onEach {
                if (rssDataID.isEmpty()) {
                    uiState = UIState.LOADING
                } else {
                    fetchNewsDetails()
                }
            }
            .launchIn(viewModelScope)
    }

    private fun fetchNewsDetails() {
        viewModelScope.launch {
            fetchNewsDetailsUseCase.invoke(rssDataID = rssDataID).collect {
                when (it) {
                    is Result.Loading -> {
                        uiState = UIState.LOADING
                    }

                    is Result.Failure -> {
                        uiState = UIState.ERROR
                    }

                    is Result.Success -> {
                        uiState = UIState.IDLE
                        newsDetail = it.data
                    }
                }
            }
        }
    }

}