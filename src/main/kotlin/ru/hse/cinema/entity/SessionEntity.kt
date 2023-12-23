package ru.hse.cinema.entity

import java.time.LocalDateTime

data class SessionEntity(
    val id: Int,
    val movie: MovieEntity,
    var timeStart: LocalDateTime,
) {
    val tickets: List<TicketEntity> = generateTickets()
    private fun generateTickets(): List<TicketEntity> {
        return (1..32).map { TicketEntity(id, it, 100.0, false) }
    }
}
