package dev.kawaiidevs.instaleap.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.kawaiidevs.instaleap.data.network.Api
import dev.kawaiidevs.instaleap.data.repository.ContentRepositoryImp
import dev.kawaiidevs.instaleap.domain.repository.ContentRepository
import dev.kawaiidevs.instaleap.domain.usecases.ContentUseCase
import dev.kawaiidevs.instaleap.domain.usecases.ContentUseCaseImpl
import javax.inject.Singleton

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteRepository(
        api: Api
    ): ContentRepository = ContentRepositoryImp(api)

    @Singleton
    @Provides
    fun provideMultimediaUseCase(
        contentRepository: ContentRepository
    ): ContentUseCase = ContentUseCaseImpl(contentRepository)

}