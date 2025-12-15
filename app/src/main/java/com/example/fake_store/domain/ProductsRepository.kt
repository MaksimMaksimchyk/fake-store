package com.example.fake_store.domain

import com.example.fake_store.data.CartDTO

interface ProductsRepository {
    suspend fun getProducts(): List<ProductModel>
    suspend fun getProduct(id: Int): ProductModel
    suspend fun createCart(cartDTO: CartDTO)
    fun getProductsFromCart(): List<ProductModel>
}