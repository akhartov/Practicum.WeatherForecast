package com.practicum.imdb

import android.app.Activity
import com.practicum.imdb.data.MoviesRepositoryImpl
import com.practicum.imdb.data.network.RetrofitNetworkClient
import com.practicum.imdb.domain.api.MoviesInteractor
import com.practicum.imdb.domain.api.MoviesRepository
import com.practicum.imdb.domain.impl.MoviesInteractorImpl
import com.practicum.imdb.presentation.movies.MoviesSearchPresenter
import com.practicum.imdb.presentation.PosterController
import com.practicum.imdb.presentation.movies.MoviesView
import com.practicum.imdb.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }

    fun provideMoviesSearchPresenter(moviesView: Activity, adapter: MoviesAdapter): MoviesSearchPresenter {
        return MoviesSearchPresenter(moviesView, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}