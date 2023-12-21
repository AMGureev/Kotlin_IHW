package ru.hse.cinema.dao

import ru.hse.cinema.entity.MovieEntity

class InMemoryMovieDao : MovieDao {
    private val movies = mutableListOf<MovieEntity>()

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
}