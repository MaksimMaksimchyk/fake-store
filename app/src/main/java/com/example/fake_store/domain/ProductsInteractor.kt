package com.example.fake_store.domain

class ProductsInteractor(private val productsRepository: ProductsRepository) {

    fun getProducts(): List<ProductModel> = productsRepository.getProducts()

    fun addToCart(product: ProductModel) {
        productsRepository.addToCart(product)
    }

}