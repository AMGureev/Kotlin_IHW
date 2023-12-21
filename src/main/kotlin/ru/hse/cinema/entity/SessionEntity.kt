package ru.hse.cinema.entity

import java.util.*

data class SessionEntity(
    val id: Int,
    val movie: MovieEntity,
    var timeStart: Date,
) {
    val tickets: List<TicketEntity> = generateTickets()
    private fun generateTickets(): List<TicketEntity> {
        return (1..32).map { TicketEntity(this, it, 100.0, false) }
    }
}