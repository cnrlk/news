package com.caneru.news

import com.caneru.news.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val navigationManager: NavigationManager,
) : BaseViewModel()