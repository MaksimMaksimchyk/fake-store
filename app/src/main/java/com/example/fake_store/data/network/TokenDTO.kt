package com.example.fake_store.data.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenDTO(
    val token: String
)