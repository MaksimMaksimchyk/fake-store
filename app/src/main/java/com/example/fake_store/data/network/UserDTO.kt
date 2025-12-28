package com.example.fake_store.data.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDTO(
    val username: String,
    val password: String
)
