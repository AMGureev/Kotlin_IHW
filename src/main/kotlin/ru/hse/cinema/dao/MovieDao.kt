package ru.hse.cinema.dao

import ru.hse.cinema.entity.MovieEntity

interface MovieDao {
    fun getAllMovies(): List<MovieEntity>
    fun addMovie(movie: MovieEntity)
    fun deleteMovie(movie: MovieEntity)
    fun isMovie(name: String): Boolean
    fun returnMovieByName(name: String): MovieEntity
    fun editMovieInformation(movie: MovieEntity, newTitle: String, newDuration: Int)
}