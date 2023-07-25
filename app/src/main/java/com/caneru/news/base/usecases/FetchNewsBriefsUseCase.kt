package com.caneru.news.base.usecases

import com.caneru.news.base.di.repositories.NewsRepository
import com.caneru.news.base.models.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchNewsBriefsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
) {

    suspend operator fun invoke() = flow {
        emit(Result.Loading(true))
        val response = newsRepository.getNewsBriefs()
        emit(Result.Success(response))
    }.catch { e ->
        emit(Result.Failure(e.message ?: "Error while fetching data"))
    }

}