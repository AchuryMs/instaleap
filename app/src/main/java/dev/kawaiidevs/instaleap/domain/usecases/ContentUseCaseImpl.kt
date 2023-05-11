package dev.kawaiidevs.instaleap.domain.usecases

import dagger.hilt.android.scopes.ActivityScoped
import dev.kawaiidevs.instaleap.domain.ResultContents
import dev.kawaiidevs.instaleap.domain.model.ContentEntity
import dev.kawaiidevs.instaleap.domain.repository.ContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityScoped
class ContentUseCaseImpl @Inject constructor(
    private val repository: ContentRepository
) : ContentUseCase {

    override suspend fun getAllContent(type: String): Flow<ResultContents<ContentEntity>> {
        return repository.getAllContent()
    }

}