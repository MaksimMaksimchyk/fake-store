package com.example.fake_store.domain

class ProductsInteractor(private val productsRepository: ProductsRepository) {

    suspend fun getProducts(): List<ProductModel> = productsRepository.getProducts()

    suspend fun addToCart(product: ProductModel) {
        productsRepository.addToCart(product)
    }

    suspend fun getProduct(id: Int): ProductModel = productsRepository.getProduct(id)

}