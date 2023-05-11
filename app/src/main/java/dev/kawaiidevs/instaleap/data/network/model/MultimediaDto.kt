package dev.kawaiidevs.instaleap.data.network.model

import dev.kawaiidevs.instaleap.domain.constants.EMPTY_STRING
import dev.kawaiidevs.instaleap.domain.model.Type

data class MultimediaDto(
    val name: String? = EMPTY_STRING,
    val type: Type?
)
