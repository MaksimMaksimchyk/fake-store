package com.example.fake_store.domain

interface ProductsRepository {
    suspend fun getProducts(): List<ProductModel>
    suspend fun addToCart(product: ProductModel)
    suspend fun getProduct(id: Int): ProductModel
}