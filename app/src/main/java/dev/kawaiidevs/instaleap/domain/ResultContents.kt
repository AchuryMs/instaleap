package dev.kawaiidevs.instaleap.domain

import dev.kawaiidevs.instaleap.domain.constants.EMPTY_INT

sealed class ResultContents<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultContents<T>()
    data class Error(val exception: String?, val errorCode: Int = EMPTY_INT) :
        ResultContents<Nothing>()

    object InternetConnectionError : ResultContents<Nothing>()
    object Loading : ResultContents<Nothing>()
}
