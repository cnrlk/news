package com.caneru.news.base.usecases

import com.caneru.news.base.di.repositories.NewsRepository
import com.caneru.news.base.models.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchNewsDetailsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(rssDataID: String) = flow {
        emit(Result.Loading(true))
        val response = newsRepository.getNewsDetail(rssDataID = rssDataID)
        emit(Result.Success(response))
    }.catch { e ->
        emit(Result.Failure(e.message ?: "Error while fetching data"))
    }

}