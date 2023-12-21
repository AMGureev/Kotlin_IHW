package ru.hse.cinema.dao

import ru.hse.cinema.entity.TicketEntity

class InMemoryTicketDao : TicketDao {
    private val soldTickets = mutableListOf<TicketEntity>()
    override fun sellTicket(ticket: TicketEntity) {
        soldTickets.add(ticket)
    }

    override fun returnTicket(ticket: TicketEntity) {
        soldTickets.remove(ticket)
    }

    override fun ticketIsSold(ticket: TicketEntity): Boolean {
        if (ticket in soldTickets) {
            return true
        }
        return false
    }

    override fun returnSoldTicketsById(id: Int): List<TicketEntity> {
        return soldTickets.filter { ticket ->
            ticket.session.id == id
        }
    }

    override fun activationTicket(ticket: TicketEntity) {
        ticket.activation = true
    }
}