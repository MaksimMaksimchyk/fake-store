package com.example.fake_store.domain

import com.example.fake_store.data.network.CartDTO

interface ProductsRepository {
    suspend fun getProducts(): List<ProductModel>
    suspend fun getProduct(id: Int): ProductModel
    suspend fun createCart(cartDTO: CartDTO)
    fun getProductsFromCart(): List<ProductModel>
    suspend fun createToken(username: String, password: String): String
}