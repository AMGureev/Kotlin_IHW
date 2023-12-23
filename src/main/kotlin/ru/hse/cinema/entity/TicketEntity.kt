package ru.hse.cinema.entity

data class TicketEntity(
    val sessionID: Int,
    val seatNumber: Int,
    val price: Double,
    var activation: Boolean
)