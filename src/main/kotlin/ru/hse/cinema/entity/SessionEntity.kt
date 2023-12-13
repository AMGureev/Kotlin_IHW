package ru.hse.cinema.entity

import java.util.*

data class SessionEntity(
    val movie: MovieEntity,
    var timeStart: Date,
    val places: List<PlaceEntity> = generatePlaces()
) {
    companion object {
        private fun generatePlaces(): List<PlaceEntity> {
            return (1..32).map { PlaceEntity(it, true) }
        }
    }
}
