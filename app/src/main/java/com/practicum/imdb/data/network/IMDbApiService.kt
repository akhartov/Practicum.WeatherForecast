package com.practicum.imdb.data.network

import com.practicum.imdb.data.dto.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/TOKEN/{expression}")
    fun searchMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>
}