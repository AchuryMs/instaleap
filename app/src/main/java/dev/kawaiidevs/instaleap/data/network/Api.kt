package dev.kawaiidevs.instaleap.data.network

import dev.kawaiidevs.instaleap.data.network.model.ContentDto
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("v3/72f66f33-9186-4b20-a9a6-2c65661bc9cf")
    suspend fun getAllContent(): Response<ContentDto>
}