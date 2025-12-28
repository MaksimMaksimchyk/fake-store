package com.example.fake_store.data

import android.util.Log
import com.example.fake_store.data.storage.AuthManager
import com.example.fake_store.data.network.CartDTO
import com.example.fake_store.data.network.FakeStoreApi
import com.example.fake_store.data.network.UserDTO
import com.example.fake_store.data.network.toDomainProduct
import kotlinx.coroutines.Dispatchers
import com.example.fake_store.data.storage.CartItem
import com.example.fake_store.data.storage.ProductsDao
import com.example.fake_store.data.storage.toDomain
import com.example.fake_store.data.storage.toEntity
import com.example.fake_store.domain.ProductInCartModel
import com.example.fake_store.domain.ProductModel
import com.example.fake_store.domain.ProductsRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val api: FakeStoreApi,
    private val dao: ProductsDao
) : ProductsRepository {

    override suspend fun getProducts(): List<ProductModel> {
        return api.getProducts().map { it.toDomainProduct() }
    }

    override suspend fun getProduct(id: Int): ProductModel {
        return api.getProduct(id).toDomainProduct()
    }

    override suspend fun createToken(username: String, password: String): String {
        val tokenDTO = api.createToken(UserDTO("mor_2314", "83r5^_"))
        return tokenDTO.token
    }

    override suspend fun addProductToCart(product: ProductModel) {
        withContext(Dispatchers.IO) {
            dao.addOrUpdate(product.toEntity())
        }
    }

    override suspend fun getProductsFromCart(): List<ProductInCartModel> {
        return dao.getProductsWithCart().map { it.toDomain() }
    }

    override suspend fun createCart(cartDTO: CartDTO) {
        api.createCart(cartDTO)
    }

    override suspend fun removeFromCart(productId: Int) {
        dao.deleteCartItemById(productId)
    }
}