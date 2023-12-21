package ru.hse.cinema.entity

data class TicketEntity(
    val session: SessionEntity,
    val seatNumber: Int,
    val price: Double,
    var activation: Boolean
)