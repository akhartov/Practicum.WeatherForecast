package com.practicum.imdb.presentation.movies


import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import com.practicum.imdb.Creator
import com.practicum.imdb.R
import com.practicum.imdb.domain.api.MoviesInteractor
import com.practicum.imdb.domain.models.Movie
import com.practicum.imdb.ui.movies.models.MoviesState

class MoviesSearchPresenter(
    private val view: MoviesView,
    private val context: Context) {
    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private var lastSearchText: String? = null

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }

    private val movies = ArrayList<Movie>()

    private val handler = Handler(Looper.getMainLooper())

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }

    fun onCreate() {
        //adapter.movies = movies
    }

    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }

    fun searchDebounce(changedText: String) {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)

        val searchRunnable = Runnable { searchRequest(changedText) }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            handler.postDelayed(
                searchRunnable,
                SEARCH_REQUEST_TOKEN,
                SEARCH_DEBOUNCE_DELAY
            )
        } else {
            val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
            handler.postAtTime(
                searchRunnable,
                SEARCH_REQUEST_TOKEN,
                postTime,
            )
        }
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            view.render(
                MoviesState.Loading
            )

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        if (foundMovies != null) {
                            movies.clear()
                            movies.addAll(foundMovies)
                        }

                        when {
                            errorMessage != null -> {
                                view.render(
                                    MoviesState.Error(
                                        errorMessage = context.getString(R.string.something_went_wrong),
                                    )
                                )
                                view.showToast(errorMessage)
                            }

                            movies.isEmpty() -> {
                                view.render(
                                    MoviesState.Empty(
                                        message = context.getString(R.string.nothing_found),
                                    )
                                )
                            }

                            else -> {
                                view.render(
                                    MoviesState.Content(
                                        movies = movies,
                                    )
                                )
                            }
                        }

                    }
                }
            })
        }
    }
}