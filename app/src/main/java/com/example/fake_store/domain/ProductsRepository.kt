package com.example.fake_store.domain

interface ProductsRepository {
    fun getProducts(): List<ProductModel>
    fun addToCart(product: ProductModel)
}