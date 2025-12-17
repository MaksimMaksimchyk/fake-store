package com.example.fake_store.domain

import com.example.fake_store.data.CartDTO
import com.example.fake_store.data.toDomainProduct
import javax.inject.Inject

class ProductsInteractor @Inject constructor(private val productsRepository: ProductsRepository) {

    suspend fun getProducts(): List<ProductModel> = productsRepository.getProducts()

    suspend fun getProduct(id: Int): ProductModel = productsRepository.getProduct(id)

    suspend fun createCart(cartDTO: CartDTO) {
        productsRepository.createCart(cartDTO)
    }

    fun getProductsFromCart(): List<ProductModel> = productsRepository.getProductsFromCart()
}