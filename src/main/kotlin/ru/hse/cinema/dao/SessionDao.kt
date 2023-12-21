package ru.hse.cinema.dao

import ru.hse.cinema.entity.SessionEntity
import java.util.*

interface SessionDao {
    fun addSession(session: SessionEntity)
    fun deleteSession(session: SessionEntity)
    fun editSession(session: SessionEntity, newDate: Date)
    fun getAllSessions(session: SessionEntity): List<SessionEntity>
    fun getID(): Int
    fun isSession(id: Int): Boolean
    fun returnSessionById(id: Int): SessionEntity
}