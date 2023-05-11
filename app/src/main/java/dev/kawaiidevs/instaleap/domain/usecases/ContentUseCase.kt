package dev.kawaiidevs.instaleap.domain.usecases

import dev.kawaiidevs.instaleap.domain.ResultContents
import dev.kawaiidevs.instaleap.domain.model.ContentEntity
import kotlinx.coroutines.flow.Flow

interface ContentUseCase {
    suspend fun getAllContent(type: String): Flow<ResultContents<ContentEntity>>
}