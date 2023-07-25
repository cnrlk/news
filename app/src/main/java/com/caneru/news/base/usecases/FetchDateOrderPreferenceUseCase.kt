package com.caneru.news.base.usecases

import com.caneru.news.base.datastore.DataStoreManager
import com.caneru.news.base.models.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchDateOrderPreferenceUseCase @Inject constructor(val dataStoreManager: DataStoreManager) {

    suspend operator fun invoke() = flow {
        emit(Result.Loading(true))
        val response = dataStoreManager.getDateOrderPreference().first()
        emit(Result.Success(response))
    }.catch {
        emit(Result.Failure(it.message ?: "Error while fetching date order preference"))
    }
}