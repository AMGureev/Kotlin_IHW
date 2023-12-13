package ru.hse.cinema.entity

data class TicketEntity(
    val session: SessionEntity,
    val seatNumber: PlaceEntity,
    val price: Double
)