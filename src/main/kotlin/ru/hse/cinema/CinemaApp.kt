package ru.hse.cinema

import ru.hse.cinema.dao.MovieDao
import ru.hse.cinema.dao.PlaceDao
import ru.hse.cinema.dao.SessionDao
import ru.hse.cinema.dao.TicketDao
import ru.hse.cinema.entity.MovieEntity
import ru.hse.cinema.entity.PlaceEntity
import ru.hse.cinema.entity.SessionEntity
import ru.hse.cinema.entity.TicketEntity
import java.util.*

class CinemaApp(
    private val movieDao: MovieDao,
    private val ticketDao: TicketDao,
    private val sessionDao: SessionDao,
    private val placeDao: PlaceDao
) {

    fun run() {
        // Здесь можно реализовать цикл взаимодействия с пользователем
    }

    fun sellTicket(session: SessionEntity, seatNumber: PlaceEntity, price: Double) {
        val ticket = TicketEntity(session, seatNumber, price)
        ticketDao.sellTicket(ticket)
        placeDao.takeThePlace(seatNumber)
    }

    fun returnTicket(ticket: TicketEntity) {
        ticketDao.returnTicket(ticket)
        placeDao.freeUpSpace(ticket.seatNumber)
    }

    fun addMovie(movie: MovieEntity) {
        movieDao.addMovie(movie)
    }

    fun addSession(movie: MovieEntity, startTime: Date) {
        val session = SessionEntity(movie, startTime)
        sessionDao.addSession(session)
    }

    fun deleteSession(session: SessionEntity) {
        val ticketsForSession = ticketDao.getTicketsForSession(session)
        ticketsForSession.forEach { ticketDao.returnTicket(it) }
        sessionDao.deleteSession(session)
    }

    fun editSession(session: SessionEntity, newDate: Date) {
        sessionDao.editSession(session, newDate)
    }

    fun editMovie(updatedMovie: MovieEntity, newDuration: Int) {
        movieDao.editMovie(updatedMovie, newDuration)
    }
    fun getTicketsForSession(session: SessionEntity){
        getTicketsForSession(session)
    }
    fun getFreePlaces(session: SessionEntity){
        getFreePlaces(session)
    }
}