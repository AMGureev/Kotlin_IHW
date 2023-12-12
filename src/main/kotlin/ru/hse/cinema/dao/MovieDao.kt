package ru.hse.cinema.dao

import ru.hse.cinema.entity.MovieEntity

interface MovieDao {
    fun getAllMovies() : List<MovieEntity>
    fun addMovie(movie: MovieEntity)
    fun deleteMovie(movie: MovieEntity)
    fun editMovie(updatedMovie: MovieEntity, newDuration: Int)
}