package dev.kawaiidevs.instaleap.domain.model

import dev.kawaiidevs.instaleap.domain.constants.EMPTY_STRING

data class MultimediaEntity(
    val name: String? = EMPTY_STRING,
    val type: Type?
)
