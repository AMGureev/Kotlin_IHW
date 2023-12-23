package ru.hse.cinema.dao

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import ru.hse.cinema.entity.MovieEntity
import ru.hse.cinema.entity.TicketEntity
import java.io.File
import kotlin.io.path.Path

class InMemoryTicketDao : TicketDao {
    private val DIRECTORY_PATH = "tickets"
    private val FILE_NAME = "soldTickets.json"
    private var soldTickets = mutableListOf<TicketEntity>()
    override fun sellTicket(ticket: TicketEntity) {
        soldTickets.add(ticket)
    }

    override fun returnTicket(ticket: TicketEntity) {
        soldTickets.remove(ticket)
    }

    override fun ticketIsSold(ticket: TicketEntity): Boolean {
        return ticket in soldTickets
    }

    override fun returnSoldTicketsById(id: Int): List<TicketEntity> {
        return soldTickets.filter { ticket ->
            ticket.sessionID == id
        }
    }

    override fun activationTicket(ticket: TicketEntity) {
        ticket.activation = true
    }

    override fun saveAllSoldTickets() {
        File(DIRECTORY_PATH).mkdirs()
        val file = Path(DIRECTORY_PATH, FILE_NAME).toFile()
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.registerKotlinModule()
        mapper.writeValue(file, soldTickets)
    }

    override fun fillingSoldTicketsData() {
        File(DIRECTORY_PATH).mkdirs()
        val file = File(DIRECTORY_PATH, FILE_NAME)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("[]")
        }
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.registerKotlinModule()
        soldTickets = mapper.readValue<MutableList<TicketEntity>>(file.readText())
    }
}