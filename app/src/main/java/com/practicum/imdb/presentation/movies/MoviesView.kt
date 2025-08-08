package com.practicum.imdb.presentation.movies

import com.practicum.imdb.ui.movies.models.MoviesState

interface MoviesView {
    // Методы, меняющие внешний вид экрана

    fun render(state: MoviesState)

    // Методы одноразовых событий

    fun showToast(additionalMessage: String)

}