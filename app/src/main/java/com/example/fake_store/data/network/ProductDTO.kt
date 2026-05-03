package com.example.fake_store.data.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDTO(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
)