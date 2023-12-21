package ru.hse.cinema.dao

import ru.hse.cinema.entity.MovieEntity
import ru.hse.cinema.entity.SessionEntity
import java.util.*

class InMemorySessionDao : SessionDao {
    private var countID = 0
    private val sessions = mutableListOf<SessionEntity>()
    override fun addSession(session: SessionEntity) {
        sessions.add(session)
    }

    override fun getID(): Int {
        return countID++
    }

    override fun isSession(id: Int): Boolean {
        return sessions.any { it.id == id }
    }

    override fun returnSessionById(id: Int): SessionEntity {
        return sessions.find { it.id == id }!!
    }

    override fun deleteSession(session: SessionEntity) {
        sessions.remove(session)
    }

    override fun editSession(session: SessionEntity, newDate: Date) {
        session.timeStart = newDate
    }

    override fun getAllSessions(session: SessionEntity): List<SessionEntity> {
        return sessions.toList()
    }
}