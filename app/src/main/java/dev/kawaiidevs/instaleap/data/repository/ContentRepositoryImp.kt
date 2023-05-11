package dev.kawaiidevs.instaleap.data.repository

import dagger.hilt.android.scopes.ActivityScoped
import dev.kawaiidevs.instaleap.data.network.Api
import dev.kawaiidevs.instaleap.data.network.model.mapToDomain
import dev.kawaiidevs.instaleap.data.retrofit.executeRetrofitRequest
import dev.kawaiidevs.instaleap.data.retrofit.handleResultRetrofit
import dev.kawaiidevs.instaleap.domain.ResultContents
import dev.kawaiidevs.instaleap.domain.model.ContentEntity
import dev.kawaiidevs.instaleap.domain.repository.ContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityScoped
class ContentRepositoryImp @Inject constructor(
    private val api: Api
) : ContentRepository {

    override suspend fun getAllContent(): Flow<ResultContents<ContentEntity>> =
        flow {
            emit(getMultimediaListHandleResultRetrofit())
        }

    private suspend fun getMultimediaListHandleResultRetrofit(): ResultContents<ContentEntity> {
        val result = executeRetrofitRequest {
            api.getAllContent()
        }
        return handleResultRetrofit(result) { it.mapToDomain() }
    }
}


