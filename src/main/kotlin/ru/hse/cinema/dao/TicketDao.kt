package ru.hse.cinema.dao

import ru.hse.cinema.entity.SessionEntity
import ru.hse.cinema.entity.TicketEntity

interface TicketDao {
    fun sellTicket(ticket: TicketEntity)
    fun returnTicket(ticket: TicketEntity)
    fun getTicketsForSession(session: SessionEntity): List<TicketEntity>
}