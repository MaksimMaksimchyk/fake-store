package com.example.fake_store.data

import com.example.fake_store.domain.ProductModel
import com.example.fake_store.domain.ProductsRepository

class ProductsRepositoryImpl() : ProductsRepository {
    override suspend fun getProducts(): List<ProductModel> {
        return RetrofitClient.api.getProducts().map { it.toDomainProduct() }
    }

    override suspend fun addToCart(product: ProductModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(id: Int): ProductModel {
        return RetrofitClient.api.getProduct(id).toDomainProduct()
    }
}