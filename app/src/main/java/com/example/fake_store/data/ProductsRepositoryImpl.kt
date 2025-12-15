package com.example.fake_store.data

import com.example.fake_store.domain.ProductModel
import com.example.fake_store.domain.ProductsRepository

class ProductsRepositoryImpl() : ProductsRepository {
    override suspend fun getProducts(): List<ProductModel> {
        return RetrofitClient.api.getProducts().map { it.toDomainProduct() }
    }

    override suspend fun getProduct(id: Int): ProductModel {
        return RetrofitClient.api.getProduct(id).toDomainProduct()
    }

    override suspend fun createCart(cartDTO: CartDTO) {
        val result = RetrofitClient.api.createCart(cartDTO)
        RetrofitClient.currentCartDTO = result.copy()
    }

    override fun getProductsFromCart(): List<ProductModel> =
        RetrofitClient.currentCartDTO.products.map { it.toDomainProduct() }

}