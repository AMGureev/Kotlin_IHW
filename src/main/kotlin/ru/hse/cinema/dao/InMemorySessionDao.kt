package ru.hse.cinema.dao

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import ru.hse.cinema.entity.MovieEntity
import ru.hse.cinema.entity.SessionEntity
import java.io.File
import java.time.LocalDateTime
import kotlin.io.path.Path

class InMemorySessionDao : SessionDao {
    private var sessions = mutableListOf<SessionEntity>()
    private val DIRECTORY_PATH = "sessions"
    private val FILE_NAME = "session.json"
    private var countID = 0
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

    override fun saveAllSessions() {
        File(DIRECTORY_PATH).mkdirs()
        val file = Path(DIRECTORY_PATH, FILE_NAME).toFile()
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.registerKotlinModule()
        mapper.writeValue(file, sessions)
    }

    override fun fillingSessionsData() {
        File(DIRECTORY_PATH).mkdirs()
        val file = File(DIRECTORY_PATH, FILE_NAME)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("[]")
        }
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.registerKotlinModule()
        sessions = mapper.readValue<MutableList<SessionEntity>>(file.readText())
        countID = sessions.size
    }

    override fun deleteSession(session: SessionEntity) {
        sessions.remove(session)
    }

    override fun editSession(session: SessionEntity, newDate: LocalDateTime) {
        session.timeStart = newDate
    }

    override fun getAllSessions(): List<SessionEntity> {
        return sessions.toList()
    }
}