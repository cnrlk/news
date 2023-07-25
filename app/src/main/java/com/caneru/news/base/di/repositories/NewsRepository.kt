package com.caneru.news.base.di.repositories

import com.caneru.news.base.models.NewsBrief
import com.caneru.news.base.models.NewsDetail
import com.caneru.news.base.models.NewsList

interface NewsRepository {

    suspend fun getNewsBriefs(): NewsList<NewsBrief>

    suspend fun getNewsDetail(rssDataID: String): NewsDetail

}