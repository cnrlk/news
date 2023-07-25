package com.caneru.news.base.di.modules

import android.content.Context
import com.caneru.news.base.datastore.DataStoreManager
import com.caneru.news.base.datastore.DataStoreManagerImpl
import com.caneru.news.base.di.apis.NewsAPI
import com.caneru.news.base.di.repositories.NewsRepository
import com.caneru.news.base.di.repositories.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideNewsAPI(retrofit: Retrofit): NewsAPI {
        return retrofit.create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManagerImpl(context)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsAPI: NewsAPI, dataStoreManager: DataStoreManager): NewsRepository {
        return NewsRepositoryImpl(newsAPI = newsAPI, dataStoreManager = dataStoreManager)
    }
}