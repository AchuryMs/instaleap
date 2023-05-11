package dev.kawaiidevs.instaleap.data.network.model


import dev.kawaiidevs.instaleap.domain.model.MultimediaEntity
import dev.kawaiidevs.instaleap.domain.model.ContentEntity as content

fun ContentDto.mapToDomain() = content(
    results.toContentEntityList()
)

fun List<MultimediaDto>.toContentEntityList(): List<MultimediaEntity> {
    return map { multimediaEntity ->
        multimediaEntity.mapToDomain()
    }
}

fun MultimediaDto.mapToDomain() = MultimediaEntity(
    name,
    type
)