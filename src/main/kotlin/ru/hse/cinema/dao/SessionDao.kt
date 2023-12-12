package ru.hse.cinema.dao

import ru.hse.cinema.entity.SessionEntity

interface SessionDao {
    fun addSession(session: SessionEntity)
    fun editSession(session: SessionEntity)
    fun deleteSession(session: SessionEntity)
}