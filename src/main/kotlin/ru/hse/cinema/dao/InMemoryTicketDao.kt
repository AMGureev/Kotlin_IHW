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
    private val directoryPath = "tickets"
    private val fileName = "soldTickets.json"
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
        File(directoryPath).mkdirs()
        val file = Path(directoryPath, fileName).toFile()
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.registerKotlinModule()
        mapper.writeValue(file, soldTickets)
    }

    override fun fillingSoldTicketsData() {
        File(directoryPath).mkdirs()
        val file = File(directoryPath, fileName)
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
