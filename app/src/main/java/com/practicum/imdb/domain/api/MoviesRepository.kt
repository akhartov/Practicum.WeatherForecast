package com.practicum.imdb.domain.api

import com.practicum.imdb.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): List<Movie>
}