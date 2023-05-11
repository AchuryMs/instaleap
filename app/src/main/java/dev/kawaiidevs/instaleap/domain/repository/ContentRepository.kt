package dev.kawaiidevs.instaleap.domain.repository

import dev.kawaiidevs.instaleap.domain.ResultContents
import dev.kawaiidevs.instaleap.domain.model.ContentEntity
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    suspend fun getAllContent(): Flow<ResultContents<ContentEntity>>
}