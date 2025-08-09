package com.practicum.imdb

import android.content.Context
import com.practicum.imdb.data.MoviesRepositoryImpl
import com.practicum.imdb.data.network.RetrofitNetworkClient
import com.practicum.imdb.domain.api.MoviesInteractor
import com.practicum.imdb.domain.api.MoviesRepository
import com.practicum.imdb.domain.impl.MoviesInteractorImpl
import com.practicum.imdb.presentation.movies.MoviesSearchPresenter
import com.practicum.imdb.presentation.poster.PosterPresenter
import com.practicum.imdb.presentation.poster.PosterView

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(context: Context): MoviesSearchPresenter {
        return MoviesSearchPresenter(context)
    }

    fun providePosterPresenter(
        posterView: PosterView,
        imageUrl: String
    ): PosterPresenter {
        return PosterPresenter(posterView, imageUrl)
    }
}