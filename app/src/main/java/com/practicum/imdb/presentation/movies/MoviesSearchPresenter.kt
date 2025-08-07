package com.practicum.imdb.presentation.movies


import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.Toast
import com.practicum.imdb.Creator
import com.practicum.imdb.R
import com.practicum.imdb.domain.api.MoviesInteractor
import com.practicum.imdb.domain.models.Movie
import com.practicum.imdb.ui.movies.MoviesAdapter

class MoviesSearchPresenter(
    private val view: MoviesView,
    private val context: Context,
    private val adapter: MoviesAdapter) {
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
        adapter.movies = movies
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
            //placeholderMessage.visibility = View.GONE
            //moviesList.visibility = View.GONE
            //progressBar.visibility = View.VISIBLE

            moviesInteractor.searchMovies(
                newSearchText,
                object : MoviesInteractor.MoviesConsumer {
                    override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                        handler.post {
                            //progressBar.visibility = View.GONE
                            if (foundMovies != null) {
                                movies.clear()
                                movies.addAll(foundMovies)
                                adapter.notifyDataSetChanged()
                                //moviesList.visibility = View.VISIBLE
                            }
                            if (errorMessage != null) {
                                showMessage(
                                    context.getString(R.string.something_went_wrong),
                                    errorMessage
                                )
                            } else if (movies.isEmpty()) {
                                showMessage(context.getString(R.string.nothing_found), "")
                            } else {
                                hideMessage()
                            }
                        }
                    }
                }
            )
        }
    }

    private fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            // Заменили работу с элементами UI на
            // вызовы методов интерфейса
            view.showPlaceholderMessage(true)
            movies.clear()
            adapter.notifyDataSetChanged()
            view.changePlaceholderText(text)
            if (additionalMessage.isNotEmpty()) {
                Toast.makeText(context, additionalMessage, Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            // Заменили работу с элементами UI на
            // вызовы методов интерфейса
            view.showPlaceholderMessage(false)
        }
    }

    private fun hideMessage() {
        // Заменили работу с элементами UI на
        // вызовы методов интерфейса
        view.showPlaceholderMessage(false)
    }
}