package ru.hse.cinema.dao

import ru.hse.cinema.entity.PlaceEntity
import ru.hse.cinema.entity.SessionEntity
import java.util.*

interface SessionDao {
    fun addSession(session: SessionEntity)
    fun deleteSession(session: SessionEntity)
    fun editSession(session: SessionEntity, newDate : Date)
    fun getFreePlaces(session: SessionEntity) : List<PlaceEntity>
}