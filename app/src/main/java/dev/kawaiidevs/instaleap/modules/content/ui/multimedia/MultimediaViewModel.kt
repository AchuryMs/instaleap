package dev.kawaiidevs.instaleap.modules.content.ui.multimedia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kawaiidevs.instaleap.domain.ResultContents
import dev.kawaiidevs.instaleap.domain.model.ContentEntity
import dev.kawaiidevs.instaleap.domain.usecases.ContentUseCase
import dev.kawaiidevs.instaleap.modules.content.entities.MultimediaItemModelUI
import dev.kawaiidevs.instaleap.modules.content.view.MultimediaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MultimediaViewModel @Inject constructor(
    private val multimediaUseCase: ContentUseCase
) : ViewModel() {

    private val _multimediaUiState = MutableStateFlow<MultimediaUiState>(MultimediaUiState.Default)
    val multimediaUiState: StateFlow<MultimediaUiState> get() = _multimediaUiState

    fun getMultimediaList(type: String) {

        viewModelScope.launch {
            multimediaUseCase.getAllContent(type).collect() { _content ->
                _multimediaUiState.value = MultimediaUiState.Loading
                handleResponseContent(_content, type)
            }
        }
    }

    private fun handleResponseContent(result: ResultContents<ContentEntity>, type: String) {
        when (result) {
            ResultContents.Loading -> {
                _multimediaUiState.value = MultimediaUiState.Loading
            }

            is ResultContents.Success -> {
                if (result.data.contentList?.isEmpty()!!) {
                    _multimediaUiState.value = MultimediaUiState.NoDataFound
                } else {
                    val contentList = result.data.contentList.let { contentList ->
                        contentList.map { multimediaEntity ->
                            MultimediaItemModelUI.mapFromDomain(multimediaEntity)
                        }
                    }
                    if (type.isEmpty()) {
                        _multimediaUiState.value = MultimediaUiState.Success(contentList)
                    } else {
                        _multimediaUiState.value =
                            MultimediaUiState.Success(contentList.filter { it.type.toString() == type })
                    }

                }
            }

            is ResultContents.Error -> {
                _multimediaUiState.value = MultimediaUiState.Error
            }

            is ResultContents.InternetConnectionError -> {
                _multimediaUiState.value =
                    MultimediaUiState.ShowNoConnectivityError
            }
        }
    }
}