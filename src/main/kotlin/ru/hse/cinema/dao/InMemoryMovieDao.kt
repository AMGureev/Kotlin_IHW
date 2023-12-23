package ru.hse.cinema.dao

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import ru.hse.cinema.entity.MovieEntity
import java.io.File
import kotlin.io.path.Path

class InMemoryMovieDao : MovieDao {
    private var movies = mutableListOf<MovieEntity>()
    private val DIRECTIRY_PATH = "movies"
    private val FILE_NAME = "movie.json"

    override fun getAllMovies(): List<MovieEntity> {
        return movies.toList()
    }

    override fun addMovie(movie: MovieEntity) {
        movies.add(movie)
    }

    override fun deleteMovie(movie: MovieEntity) {
        movies.remove(movie)
    }

    override fun isMovie(name: String): Boolean {
        return movies.any { it.title == name }
    }

    override fun returnMovieByName(name: String): MovieEntity {
        return movies.find { it.title == name }!!
    }

    override fun editMovieInformation(movie: MovieEntity, newTitle: String, newDuration: Int) {
        movie.title = newTitle
        movie.duration = newDuration
    }

    override fun saveAllMovies() {
        File(DIRECTIRY_PATH).mkdirs()
        val file = Path(DIRECTIRY_PATH, FILE_NAME).toFile()
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.registerKotlinModule()
        mapper.writeValue(file, movies)
    }

    override fun fillingMoviesData() {
        File(DIRECTIRY_PATH).mkdirs()
        val file = File(DIRECTIRY_PATH, FILE_NAME)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("[]")
        }
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.registerKotlinModule()
        movies = mapper.readValue<MutableList<MovieEntity>>(file.readText())
    }
}