package ru.hse.cinema.dao

import ru.hse.cinema.entity.MovieEntity
import ru.hse.cinema.entity.SessionEntity
import ru.hse.cinema.entity.PlaceEntity
import ru.hse.cinema.entity.TicketEntity
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

    override fun getFreePlaces(session: SessionEntity) : List<PlaceEntity> {
        return session.places.filter { it.usage }
        TODO("нихуя не работает это место")
    }

}