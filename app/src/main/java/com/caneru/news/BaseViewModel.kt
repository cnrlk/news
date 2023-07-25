package com.caneru.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.caneru.news.ui.UIState

open class BaseViewModel : ViewModel() {

    var uiState by mutableStateOf(UIState.IDLE)

    fun isLoading(): Boolean {
        return this.uiState == UIState.LOADING
    }

    fun isIdle(): Boolean {
        return this.uiState == UIState.IDLE
    }

    fun isError(): Boolean {
        return this.uiState == UIState.ERROR
    }
}