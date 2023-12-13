package ru.hse.cinema.dao

import ru.hse.cinema.entity.MovieEntity
import ru.hse.cinema.entity.SessionEntity
import java.util.*

class InMemorySessionDao : SessionDao{
    private val sessions = mutableListOf<SessionEntity>()
    override fun addSession(session: SessionEntity) {
        sessions.add(session)
    }

    override fun deleteSession(session: SessionEntity) {
        sessions.remove(session)
    }

    override fun editSession(session: SessionEntity, newDate: Date) {
        val existingSession = sessions.find { it.timeStart == session.timeStart }
        existingSession?.let {
            it.timeStart = newDate
        }
    }
}