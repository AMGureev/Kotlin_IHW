package ru.hse.cinema.dao

import ru.hse.cinema.entity.MovieEntity
import ru.hse.cinema.entity.SessionEntity
import java.util.*

interface SessionDao {
    fun addSession(session: SessionEntity)
    fun editSession(session: SessionEntity)
    fun returnAllTickets(session: SessionEntity)
    fun deleteSession(session: SessionEntity)
}