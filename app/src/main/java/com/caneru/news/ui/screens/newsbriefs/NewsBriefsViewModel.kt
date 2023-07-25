package com.caneru.news.ui.screens.newsbriefs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.caneru.news.BaseViewModel
import com.caneru.news.base.models.DatePreference
import com.caneru.news.base.models.NewsBrief
import com.caneru.news.base.models.Result
import com.caneru.news.base.usecases.FetchDateOrderPreferenceUseCase
import com.caneru.news.base.usecases.FetchNewsBriefsUseCase
import com.caneru.news.base.usecases.UpdateDateOrderPreferenceUseCase
import com.caneru.news.ui.UIState
import com.caneru.news.ui.navigation.NavigationManager
import com.caneru.news.ui.utils.DateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsBriefsViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val fetchNewsBriefsUseCase: FetchNewsBriefsUseCase,
    private val fetchDateOrderPreferenceUseCase: FetchDateOrderPreferenceUseCase,
    private val updateDateOrderPreferenceUseCase: UpdateDateOrderPreferenceUseCase
) : BaseViewModel() {

    var newsBriefs by mutableStateOf<List<NewsBrief>>(listOf())
    var dateOrderPreference by mutableStateOf(DatePreference.DESCENDING)

    init {
        fetchDateOrder()
        fetchNewsBriefs()
    }

    fun updateDateOrder(preference: DatePreference) {
        viewModelScope.launch {
            updateDateOrderPreferenceUseCase.invoke(preference = preference).collect {
                when (it) {
                    is Result.Loading -> {
                        uiState = UIState.LOADING
                    }

                    is Result.Failure -> {
                        uiState = UIState.ERROR
                    }

                    is Result.Success -> {
                        uiState = UIState.IDLE
                        dateOrderPreference = it.data
                        updateListSorting()
                    }
                }
            }
        }
    }

    private fun updateListSorting() {
        newsBriefs = if (dateOrderPreference == DatePreference.DESCENDING) {
            newsBriefs.sortedByDescending { data -> DateFormatter.format(data.pubDate) }
        } else {
            newsBriefs.sortedBy { data -> DateFormatter.format(data.pubDate) }
        }
    }

    private fun fetchDateOrder() {
        viewModelScope.launch {
            fetchDateOrderPreferenceUseCase.invoke().collect {
                when (it) {
                    is Result.Loading -> {
                        uiState = UIState.LOADING
                    }

                    is Result.Failure -> {
                        uiState = UIState.ERROR
                    }

                    is Result.Success -> {
                        uiState = UIState.IDLE
                        dateOrderPreference = it.data
                        updateListSorting()
                    }
                }
            }
        }
    }

    private fun fetchNewsBriefs() {
        viewModelScope.launch {
            fetchNewsBriefsUseCase.invoke().collect {
                when (it) {
                    is Result.Loading -> {
                        uiState = UIState.LOADING
                    }

                    is Result.Failure -> {
                        uiState = UIState.ERROR
                    }

                    is Result.Success -> {
                        uiState = UIState.IDLE
                        newsBriefs = it.data.newsList
                        updateListSorting()
                    }
                }
            }
        }
    }

    fun onNewsBriefItemTapped(rssDataID: String) {
        navigationManager.navigate("NewsDetails&rssDataID=$rssDataID")
    }

}