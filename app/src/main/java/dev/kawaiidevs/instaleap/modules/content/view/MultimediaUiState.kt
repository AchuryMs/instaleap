package dev.kawaiidevs.instaleap.modules.content.view


import dev.kawaiidevs.instaleap.modules.content.entities.MultimediaItemModelUI


sealed class MultimediaUiState {
    data class Success(val data: List<MultimediaItemModelUI>?) : MultimediaUiState()
    object Loading : MultimediaUiState()
    object ShowNoConnectivityError : MultimediaUiState()
    object Error : MultimediaUiState()
    object NoDataFound : MultimediaUiState()
    object Default : MultimediaUiState()
}