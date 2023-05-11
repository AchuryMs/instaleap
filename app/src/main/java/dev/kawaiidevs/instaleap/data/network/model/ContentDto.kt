package dev.kawaiidevs.instaleap.data.network.model

import com.google.gson.annotations.SerializedName

data class ContentDto(

    @SerializedName("results")
    val results: List<MultimediaDto>
)
