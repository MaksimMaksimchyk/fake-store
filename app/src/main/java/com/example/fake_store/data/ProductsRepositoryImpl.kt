package com.example.fake_store.data

import com.example.fake_store.data.storage.AuthManager
import com.example.fake_store.data.network.CartDTO
import com.example.fake_store.data.network.FakeStoreApi
import com.example.fake_store.data.network.UserDTO
import com.example.fake_store.data.network.toDomainProduct
import com.example.fake_store.domain.ProductModel
import com.example.fake_store.domain.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val api: FakeStoreApi,
    private val cartRepository: CartRepository
) : ProductsRepository {

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

    override suspend fun createToken(username: String, password: String): String {
        val tokenDTO = api.createToken(UserDTO("mor_2314", "83r5^_"))
        return tokenDTO.token
    }
}