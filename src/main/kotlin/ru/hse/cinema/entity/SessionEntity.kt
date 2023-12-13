package ru.hse.cinema.entity

import ru.hse.cinema.entity.TicketEntity
import java.util.*

data class SessionEntity(
    val movie: MovieEntity,
    var timeStart: Date,
)
