package ru.hse.cinema.dao

import com.sun.org.apache.xpath.internal.operations.Bool
import ru.hse.cinema.entity.SessionEntity
import ru.hse.cinema.entity.TicketEntity

interface TicketDao {
    fun sellTicket(ticket: TicketEntity)
    fun returnTicket(ticket: TicketEntity)
    fun ticketIsSold(ticket: TicketEntity): Boolean
    fun returnSoldTicketsById(id: Int): List<TicketEntity>
    fun activationTicket(ticket: TicketEntity)
}