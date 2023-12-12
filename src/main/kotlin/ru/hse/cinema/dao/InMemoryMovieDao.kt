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

    override fun editMovie(updatedMovie: MovieEntity) {
        val existingMovie = movies.find { it.title == updatedMovie.title }
        existingMovie?.let {
            it.title = updatedMovie.title
            it.duration = updatedMovie.duration
        }
    }
}