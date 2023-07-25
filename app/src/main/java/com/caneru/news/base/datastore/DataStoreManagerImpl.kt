package com.caneru.news.base.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.caneru.news.base.datastore.DataStoreManager.Companion.DATE_ORDER_PREFERENCE
import com.caneru.news.base.datastore.DataStoreManager.Companion.NEWS_BRIEFS_TOKEN
import com.caneru.news.base.datastore.DataStoreManager.Companion.NEWS_DETAILS_TOKEN
import com.caneru.news.base.models.DatePreference
import com.caneru.news.base.models.NewsBrief
import com.caneru.news.base.models.NewsDetail
import com.caneru.news.base.models.NewsList
import com.caneru.news.ui.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.reflect.Type

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(Constants.API_CALL_RESPONSE)

class DataStoreManagerImpl(
    private val context: Context,
    private val gson: Gson
) : DataStoreManager {

    override suspend fun saveNewsBriefs(response: NewsList<NewsBrief>) {
        context.datastore.edit {
            it[NEWS_BRIEFS_TOKEN] = gson.toJson(response).toString()
        }
    }

    override suspend fun saveNewsDetails(response: NewsList<NewsDetail>) {
        context.datastore.edit {
            it[NEWS_DETAILS_TOKEN] = gson.toJson(response).toString()
        }
    }

    override suspend fun saveDateOrderPreference(preference: DatePreference) {
        context.datastore.edit {
            it[DATE_ORDER_PREFERENCE] = gson.toJson(preference).toString()
        }
    }

    override suspend fun getDateOrderPreference(): Flow<DatePreference> {
        return context.datastore.data.map {
            val returnType: Type = object : TypeToken<DatePreference>() {}.type
            val returnValueAsString: String =
                it[DATE_ORDER_PREFERENCE] ?: gson.toJson(DatePreference.DESCENDING).toString()
            gson.fromJson(returnValueAsString, returnType)
        }
    }

    override suspend fun getNewsBriefs(): Flow<NewsList<NewsBrief>?> {
        return context.datastore.data.map {
            val returnType: Type = object : TypeToken<NewsList<NewsBrief>>() {}.type
            val returnValueAsString: String = it[NEWS_BRIEFS_TOKEN] ?: ""
            gson.fromJson(returnValueAsString, returnType)
        }
    }

    override suspend fun getNewsDetails(): Flow<NewsList<NewsDetail>> {
        return context.datastore.data.map {
            val returnType: Type = object : TypeToken<NewsList<NewsDetail>>() {}.type
            val returnValueAsString: String = it[NEWS_DETAILS_TOKEN] ?: ""
            gson.fromJson(returnValueAsString, returnType)
        }
    }

}