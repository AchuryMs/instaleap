package dev.kawaiidevs.instaleap.modules.content.entities

import dev.kawaiidevs.instaleap.domain.constants.EMPTY_STRING
import dev.kawaiidevs.instaleap.domain.model.MultimediaEntity
import dev.kawaiidevs.instaleap.domain.model.Type
import java.io.Serializable

data class MultimediaItemModelUI(
    val name: String? = EMPTY_STRING,
    val type: Type?
) : Serializable {
    companion object {
        fun mapFromDomain(multimediaItem: MultimediaEntity) = MultimediaItemModelUI(
            multimediaItem.name,
            multimediaItem.type
        )
    }
}
