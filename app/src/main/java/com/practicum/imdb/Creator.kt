package com.practicum.imdb

import android.app.Activity
import android.content.Context
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
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(moviesView: MoviesView, context: Context, adapter: MoviesAdapter): MoviesSearchPresenter {
        return MoviesSearchPresenter(moviesView, context, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}