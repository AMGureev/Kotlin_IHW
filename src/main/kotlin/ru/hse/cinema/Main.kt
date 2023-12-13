package ru.hse.cinema

import ru.hse.cinema.dao.InMemoryMovieDao
import ru.hse.cinema.dao.InMemoryPlaceDao
import ru.hse.cinema.dao.InMemoryTicketDao
import ru.hse.cinema.dao.InMemorySessionDao
import ru.hse.cinema.entity.MovieEntity
import ru.hse.cinema.entity.PlaceEntity
import ru.hse.cinema.entity.SessionEntity
import ru.hse.cinema.entity.TicketEntity
import java.util.*

fun main() {
    val movieDao = InMemoryMovieDao()
    val ticketDao = InMemoryTicketDao()
    val sessionDao = InMemorySessionDao()
    val placeDao = InMemoryPlaceDao()
    val cinemaApp = CinemaApp(movieDao, ticketDao, sessionDao, placeDao)

    val movie = MovieEntity("Inception", 150)
    val date = Date()
    val movie2 = MovieEntity("Hello there!", 90)
    val date2 = Date()
    cinemaApp.addMovie(movie)
    cinemaApp.addSession(movie, date)
    movieDao.editMovie(movie, 100)
    val session = SessionEntity(movie, date)
    val session2 = SessionEntity(movie2, date2)
    val place = PlaceEntity(3, true)
    val ticket = TicketEntity(session, place, 123.1)
    cinemaApp.sellTicket(session, place, 123.1)
    cinemaApp.returnTicket(ticket)
    movieDao.editMovie(movie, 50)
    cinemaApp.getFreePlaces(session)
    cinemaApp.deleteSession(session)
}