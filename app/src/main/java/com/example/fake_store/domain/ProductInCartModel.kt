package com.example.fake_store.domain

data class ProductInCartModel(
    val product: ProductModel,
    val quantity: Int
)