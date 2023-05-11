package dev.kawaiidevs.instaleap.modules.content.ui.multimedia

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.kawaiidevs.instaleap.domain.ResultContents
import dev.kawaiidevs.instaleap.domain.model.ContentEntity
import dev.kawaiidevs.instaleap.domain.model.MultimediaEntity
import dev.kawaiidevs.instaleap.domain.model.Type
import dev.kawaiidevs.instaleap.domain.usecases.ContentUseCase
import dev.kawaiidevs.instaleap.modules.content.entities.MultimediaItemModelUI
import dev.kawaiidevs.instaleap.modules.content.view.MultimediaUiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
internal class MultimediaViewModelTest {
    val NAME = "DOCTOR WHO"
    val MOVIE ="MOVIE"


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var contentUseCase: ContentUseCase

    private lateinit var viewModel: MultimediaViewModel

    @Before
    fun setup() {
        viewModel = MultimediaViewModel(contentUseCase)
    }

    private fun charge_multimedia_movie(): MultimediaEntity {
        return MultimediaEntity(
            NAME, Type.MOVIE
        )
    }
    private fun charge_multimedia_series(): MultimediaEntity {
        return MultimediaEntity(
            NAME, Type.SERIES
        )
    }

    @Test
    suspend fun getMultimediaList_with_empty_typ_should_return_Success() {
        // Arrange
        val type = ""

        val contentEntity = ContentEntity(
        contentList = listOf(
            charge_multimedia_movie(),
            charge_multimedia_movie(),
            charge_multimedia_movie(),
            charge_multimedia_series(),
            charge_multimedia_series(),
            charge_multimedia_series()
        ))

        val result = ResultContents.Success(contentEntity)
        `when`(contentUseCase.getAllContent(type)).thenReturn(flowOf(result))

        // Act
        viewModel.getMultimediaList(type)

        // Assert
        val expectedState = MultimediaUiState.Success(
            contentEntity.contentList?.map { MultimediaItemModelUI.mapFromDomain(it) }
        )
        assertEquals(expectedState, viewModel.multimediaUiState.value)
    }

    @Test
    suspend fun getMultimediaList_non_empty_type_should_return_Succes_state_filtered() {
        // Arrange
        val type = MOVIE
        val contentList = ContentEntity(
        contentList = listOf(
            charge_multimedia_movie(),
            charge_multimedia_movie(),
            charge_multimedia_movie(),
            charge_multimedia_series(),
            charge_multimedia_series(),
            charge_multimedia_series()
        ))
        val result = ResultContents.Success(contentList)
        `when`(contentUseCase.getAllContent(type)).thenReturn(flowOf(result))

        // Act
        viewModel.getMultimediaList(type)

        // Assert
        val expectedState = MultimediaUiState.Success(
            contentList.contentList?.filter { it.type!!.equals(type) }?.map { MultimediaItemModelUI.mapFromDomain(it) }
        )
        assertEquals(expectedState, viewModel.multimediaUiState.value)
    }
}