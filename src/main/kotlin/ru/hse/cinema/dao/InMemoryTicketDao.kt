package ru.hse.cinema.dao

import ru.hse.cinema.entity.TicketEntity
import ru.hse.cinema.entity.SessionEntity
import java.util.*

class InMemoryTicketDao : TicketDao {
    private val soldTickets = mutableListOf<TicketEntity>()
    override fun sellTicket(ticket: TicketEntity) {
        soldTickets.add(ticket)
    }

    override fun returnTicket(ticket: TicketEntity) {
        soldTickets.remove(ticket)
    }

    override fun getTicketsForSession(session: SessionEntity): List<TicketEntity> {
        return soldTickets.filter { it.session == session }
    }

}