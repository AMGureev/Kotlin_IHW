package ru.hse.cinema

import ru.hse.cinema.dao.InMemoryMovieDao
import ru.hse.cinema.dao.InMemoryTicketDao
import ru.hse.cinema.dao.InMemorySessionDao
import ru.hse.cinema.entity.MovieEntity
import java.util.*

fun main() {
    val movieDao = InMemoryMovieDao()
    val ticketDao = InMemoryTicketDao()
    val sessionDao = InMemorySessionDao()
    val cinemaApp = CinemaApp(movieDao, ticketDao, sessionDao)

    val movie = MovieEntity("Inception", 150)
    val date = Date()

    cinemaApp.addMovie(movie)
    cinemaApp.addSession(movie, date)
}