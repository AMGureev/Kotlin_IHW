package ru.hse.cinema

import ru.hse.cinema.dao.InMemoryMovieDao
import ru.hse.cinema.dao.InMemoryTicketDao
import ru.hse.cinema.dao.InMemorySessionDao
import ru.hse.cinema.entity.MovieEntity
import ru.hse.cinema.entity.SessionEntity
import java.util.*

fun main() {
    val movieDao = InMemoryMovieDao()
    val ticketDao = InMemoryTicketDao()
    val sessionDao = InMemorySessionDao()
    val cinemaApp = CinemaApp(movieDao, ticketDao, sessionDao)

    val movie = MovieEntity("Inception", 150)
    val date = Date()
    val movie2 = MovieEntity("Hello there!", 90)
    val date2 = Date()
    cinemaApp.addMovie(movie)
    movieDao.editMovie(movie)
    cinemaApp.addSession(movie, date)
    val session = SessionEntity(movie, date)
    val session2 = SessionEntity(movie2, date2)
    cinemaApp.sellTicket(session, 3, 123.1)
    cinemaApp.sellTicket(session2, 5, 40.5)
    cinemaApp.deleteSession(session)
}