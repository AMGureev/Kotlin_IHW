package ru.hse.cinema.dao

import ru.hse.cinema.entity.SessionEntity

class InMemorySessionDao : SessionDao{
    private val sessions = mutableListOf<SessionEntity>()
    override fun addSession(session: SessionEntity) {
        sessions.add(session)
    }

    override fun editSession(session: SessionEntity) {

    }

    override fun deleteSession(session: SessionEntity) {
        sessions.remove(session)
    }
}