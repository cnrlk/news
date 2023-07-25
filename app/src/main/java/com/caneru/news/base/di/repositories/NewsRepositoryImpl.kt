package com.caneru.news.base.di.repositories

import com.caneru.news.base.datastore.DataStoreManager
import com.caneru.news.base.di.apis.NewsAPI
import com.caneru.news.base.models.NewsBrief
import com.caneru.news.base.models.NewsDetail
import com.caneru.news.base.models.NewsList
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsAPI: NewsAPI,
    private val dataStoreManager: DataStoreManager
) : NewsRepository {
    override suspend fun getNewsBriefs(): NewsList<NewsBrief> {
        var response = dataStoreManager.getNewsBriefs().first()
        if (response == null) {
            response = newsAPI.getNewsBriefs()
            dataStoreManager.saveNewsBriefs(response = response)
        }
        return response
    }

    override suspend fun getNewsDetail(rssDataID: String): NewsDetail {
        var response: NewsList<NewsDetail>? = dataStoreManager.getNewsDetails().first()
        if (response == null) {
            response = newsAPI.getNewsDetails()
            dataStoreManager.saveNewsDetails(response = response)
        }

        return response.newsList.first { it.rssDataID == rssDataID }
    }
}