package com.example.fake_store.data.network

import com.example.fake_store.data.network.ProductDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartDTO(
    val id: Int,
    val userid: Int?,
    val products: List<ProductDTO>
)