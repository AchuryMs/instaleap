package dev.kawaiidevs.instaleap.data.retrofit

import dev.kawaiidevs.instaleap.domain.ResultContents
import dev.kawaiidevs.instaleap.domain.constants.INTERNET_CONNECTION_ERROR_MESSAGE
import retrofit2.Response

internal inline fun <T : Any> executeRetrofitRequest(request: () -> Response<T>): ResultContents<T> {
    return try {
        val response = request.invoke()
        return if (response.isSuccessful && response.body() != null) {
            val body = response.body()
            if (body != null) {
                ResultContents.Success(body)
            } else {
                ResultContents.Error("Empty body found in this request")
            }
        } else {
            val errorBody = response.errorBody()
            val errorText = if (errorBody == null) "Error body null" else errorBody.string()
            ResultContents.Error(errorText, response.code())
        }
    } catch (ex: Exception) {
        ResultContents.Error(ex.message)
    }
}

fun <Api : Any, Data : Any> handleResultRetrofit(
    result: ResultContents<Api>,
    onSuccess: (Api) -> Data
): ResultContents<Data> {
    return when (result) {
        is ResultContents.Error -> {
            if (result.exception?.contains(INTERNET_CONNECTION_ERROR_MESSAGE) == true) {
                ResultContents.InternetConnectionError
            } else {
                result
            }
        }

        is ResultContents.Success -> ResultContents.Success(onSuccess.invoke(result.data))
        else -> ResultContents.Loading
    }
}

internal inline fun <Api : Any, Domain : Any> mapDomainDataState(
    apiDataState: ResultContents<Api>,
    block: Api.() -> Domain
): ResultContents<Domain> {
    return when (apiDataState) {
        is ResultContents.Success -> {
            return ResultContents.Success(block.invoke(apiDataState.data))
        }

        is ResultContents.Error -> ResultContents.Error(apiDataState.exception)
        is ResultContents.InternetConnectionError -> ResultContents.InternetConnectionError
        else -> ResultContents.Loading
    }
}

internal inline fun <Helper : Any, Api : Any, Domain : Any> mapDomainDataState(
    apiDataState: ResultContents<Api>,
    helper: Helper,
    block: (Helper, Api) -> Domain
): ResultContents<Domain> {
    return when (apiDataState) {
        is ResultContents.Success -> {
            return ResultContents.Success(block.invoke(helper, apiDataState.data))
        }

        is ResultContents.Error -> ResultContents.Error(apiDataState.exception)
        is ResultContents.InternetConnectionError -> ResultContents.InternetConnectionError
        else -> ResultContents.Loading
    }
}