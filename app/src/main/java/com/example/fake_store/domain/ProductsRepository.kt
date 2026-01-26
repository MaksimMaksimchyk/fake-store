package com.example.fake_store.domain

import com.example.fake_store.data.network.CartDTO
import com.example.fake_store.data.network.CurrencyRateDTO

interface ProductsRepository {
    suspend fun getProducts(): List<ProductModel>
    suspend fun getProduct(id: Int): ProductModel
    suspend fun createCart(cartDTO: CartDTO)
    suspend fun getProductsFromCart(): List<ProductInCartModel>
    suspend fun createToken(username: String, password: String): String
    suspend fun addProductToCart(product: ProductModel)
    suspend fun removeFromCart(productId: Int)
    suspend fun updateProductPrice(product: ProductModel, newPrice: Double)
    suspend fun getCurrencyRate(currencyId: Int): CurrencyRateDTO
}