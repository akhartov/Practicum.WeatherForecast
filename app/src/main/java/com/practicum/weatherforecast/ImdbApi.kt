package com.practicum.weatherforecast

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class ImqbResponse(
    val searchType: String,
    val expression: String,
    @SerializedName("results") val movies: ArrayList<Movie>
)

interface ImdbApi {
    @GET("/en/API/SearchMovie/{token}/{expression}")
    fun getMovieList(
        @Path("token") token: String,
        @Path("expression") expression: String
    ): Call<ImqbResponse>
}