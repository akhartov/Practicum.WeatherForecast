package com.practicum.imdb.data

import com.practicum.imdb.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
}