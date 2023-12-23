package ru.hse.cinema.dao

import ru.hse.cinema.entity.MovieEntity

interface MovieDao {
    fun getAllMovies(): List<MovieEntity> // return all movies
    fun addMovie(movie: MovieEntity) // add new movie in mutable list
    fun deleteMovie(movie: MovieEntity) // delete movie from mutable list
    fun isMovie(name: String): Boolean // search movie in mutable list and return search status
    fun returnMovieByName(name: String): MovieEntity // search movie in mutable list and return it
    fun editMovieInformation(movie: MovieEntity, newTitle: String, newDuration: Int) // edit information about movie
    fun saveAllMovies() // save all movies in json file
    fun fillingMoviesData() // data recovery from file
}