package ru.hse.cinema.dao

import ru.hse.cinema.entity.TicketEntity

interface TicketDao {
    fun sellTicket(ticket: TicketEntity) // sell the ticket - add ticket with mutable list
    fun returnTicket(ticket: TicketEntity) // return the ticket to the user - delete ticket with mutable list
    fun ticketIsSold(ticket: TicketEntity): Boolean // return status available purchased tickets
    fun returnSoldTicketsById(id: Int): List<TicketEntity> // return list of all sold tickets
    fun activationTicket(ticket: TicketEntity) // activation ticket
    fun saveAllSoldTickets() // save all sold tickets in json file
    fun fillingSoldTicketsData() // data recovery from file
}