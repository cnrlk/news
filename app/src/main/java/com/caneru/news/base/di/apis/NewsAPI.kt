package com.caneru.news.base.di.apis

import com.caneru.news.base.models.NewsBrief
import com.caneru.news.base.models.NewsDetail
import com.caneru.news.base.models.NewsList
import retrofit2.http.GET

interface NewsAPI {

    @GET("v1/f4e650de-fa86-406f-b62e-2ef542c15098")
    suspend fun getNewsBriefs(): NewsList<NewsBrief>

    @GET("v1/38bbc497-a05c-43f7-8d63-26b755fed519")
    suspend fun getNewsDetails(): NewsList<NewsDetail>
}