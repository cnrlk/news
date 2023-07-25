package com.caneru.news.base.datastore

import androidx.datastore.preferences.core.stringPreferencesKey
import com.caneru.news.base.models.DatePreference
import com.caneru.news.base.models.NewsBrief
import com.caneru.news.base.models.NewsDetail
import com.caneru.news.base.models.NewsList
import com.caneru.news.ui.utils.Constants
import kotlinx.coroutines.flow.Flow

interface DataStoreManager {

    companion object {

        val NEWS_BRIEFS_TOKEN = stringPreferencesKey(Constants.NEWS_BRIEFS)
        val NEWS_DETAILS_TOKEN = stringPreferencesKey(Constants.NEWS_DETAILS)
        val DATE_ORDER_PREFERENCE = stringPreferencesKey(Constants.DATE_ORDER_PREFERENCE)

    }

    suspend fun saveDateOrderPreference(preference: DatePreference)

    suspend fun getDateOrderPreference(): Flow<DatePreference>

    suspend fun getNewsBriefs(): Flow<NewsList<NewsBrief>?>

    suspend fun getNewsDetails(): Flow<NewsList<NewsDetail>?>

    suspend fun saveNewsBriefs(response: NewsList<NewsBrief>)

    suspend fun saveNewsDetails(response: NewsList<NewsDetail>)
}