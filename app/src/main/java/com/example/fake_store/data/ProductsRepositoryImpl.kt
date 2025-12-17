package com.example.fake_store.data

import com.example.fake_store.domain.ProductModel
import com.example.fake_store.domain.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val api: FakeStoreApi, private val cartRepository: CartRepository) : ProductsRepository {

    override suspend fun getProducts(): List<ProductModel> {
        return api.getProducts().map { it.toDomainProduct() }
    }

    override suspend fun getProduct(id: Int): ProductModel {
        return api.getProduct(id).toDomainProduct()
    }

    override suspend fun createCart(cartDTO: CartDTO) {
        val result = api.createCart(cartDTO)
        cartRepository.currentCartDTO = result.copy()
    }

    override fun getProductsFromCart(): List<ProductModel> =
        cartRepository.currentCartDTO.products.map { it.toDomainProduct() }



}