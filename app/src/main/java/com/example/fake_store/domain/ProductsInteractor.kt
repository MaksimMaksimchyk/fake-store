package com.example.fake_store.domain

import com.example.fake_store.data.network.CartDTO
import javax.inject.Inject

class ProductsInteractor @Inject constructor(private val productsRepository: ProductsRepository) {

    suspend fun getProducts(): List<ProductModel> = productsRepository.getProducts()

    suspend fun getProduct(id: Int): ProductModel = productsRepository.getProduct(id)

    suspend fun createCart(cartDTO: CartDTO) {
        productsRepository.createCart(cartDTO)
    }

    suspend fun getProductsFromCart(): List<ProductInCartModel> {
        return productsRepository.getProductsFromCart()
    }

    suspend fun addProductToCart(product: ProductModel) {
        productsRepository.addProductToCart(product)
    }

    suspend fun removeFromCart(productId: Int) {
        productsRepository.removeFromCart(productId)
    }

    suspend fun createToken(username: String, password: String): String {
        return productsRepository.createToken(username, password)
    }

    suspend fun updateProductPrice(product: ProductModel, newPrice: Double) {
        productsRepository.updateProductPrice(product, newPrice)
    }
}