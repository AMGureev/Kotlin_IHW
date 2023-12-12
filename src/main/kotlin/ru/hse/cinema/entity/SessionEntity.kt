package ru.hse.cinema.entity

import java.util.*

data class SessionEntity(
    val movie: MovieEntity,
    val timeStart: Date,
)
