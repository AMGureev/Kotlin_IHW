package ru.hse.cinema.dao

import ru.hse.cinema.entity.SessionEntity

class InMemorySessionDao : SessionDao{
    private val sessions = mutableListOf<SessionEntity>()
    override fun addSession(session: SessionEntity) {
        sessions.add(session)
    }

    override fun editSession(session: SessionEntity) {
        TODO("Not yet implemented")
    }

    override fun returnAllTickets(session: SessionEntity) {
        TODO("Not yet implemented")
    }

    override fun deleteSession(session: SessionEntity) {
        returnAllTickets(session)
        sessions.remove(session)
    }
}