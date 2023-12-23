package ru.hse.cinema.dao

import ru.hse.cinema.entity.SessionEntity
import java.time.LocalDateTime

interface SessionDao {
    fun addSession(session: SessionEntity) // add new session in mutable list
    fun deleteSession(session: SessionEntity) // delete session from mutable list
    fun editSession(session: SessionEntity, newDate: LocalDateTime) // edit information about session
    fun getAllSessions(): List<SessionEntity> // return list with all sessions
    fun getID(): Int // incrementing the id counter
    fun isSession(id: Int): Boolean // search for a session by id and return search status
    fun returnSessionById(id: Int): SessionEntity // search for a session by id and return it
    fun saveAllSessions() // save all sessions in json file
    fun fillingSessionsData() // data recovery from file
}