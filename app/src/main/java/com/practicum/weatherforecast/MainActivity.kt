package com.practicum.weatherforecast

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var placeholderMessage: TextView

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://tv-api.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(ImdbApi::class.java)

    private val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        placeholderMessage = findViewById(R.id.placeholderMessage)
        placeholderMessage.visibility = View.VISIBLE
        findViewById<Button>(R.id.search_button).setOnClickListener {
            val text = findViewById<EditText>(R.id.edit_query).text
            service.getMovieList("MY_TOKEN", text.toString()).enqueue(object : Callback<ImqbResponse> {
                override fun onResponse(
                    call: Call<ImqbResponse>,
                    response: Response<ImqbResponse>
                ) {
                    movieAdapter.movies = response.body()?.movies?:ArrayList()
                }

                override fun onFailure(call: Call<ImqbResponse>, t: Throwable) {
                    showMessage("Что-то пошло не так...", t.message.toString())
                }

            })
        }

        findViewById<RecyclerView>(R.id.movies).apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = movieAdapter
        }
    }


    private fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            placeholderMessage.visibility = View.VISIBLE
            movieAdapter.movies = ArrayList()
            placeholderMessage.text = text
            if (additionalMessage.isNotEmpty()) {
                Toast.makeText(applicationContext, additionalMessage, Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            placeholderMessage.visibility = View.GONE
        }
    }
}