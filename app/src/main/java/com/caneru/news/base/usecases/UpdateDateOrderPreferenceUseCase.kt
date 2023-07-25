package com.caneru.news.base.usecases

import com.caneru.news.base.datastore.DataStoreManager
import com.caneru.news.base.models.DatePreference
import com.caneru.news.base.models.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateDateOrderPreferenceUseCase @Inject constructor(private val dataStoreManager: DataStoreManager) {

    suspend operator fun invoke(preference: DatePreference) = flow {
        emit(Result.Loading(true))
        dataStoreManager.saveDateOrderPreference(preference = preference)
        emit(Result.Success(preference))
    }.catch {
        emit(Result.Failure(it.message ?: "Error while updating date order preference"))
    }
}