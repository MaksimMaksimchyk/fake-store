package com.example.fake_store.data

import com.example.fake_store.dagger2.BYNConverterApi
import com.example.fake_store.dagger2.BaseFakeStoreApi
import com.example.fake_store.data.network.CartDTO
import com.example.fake_store.data.network.CurrencyApi
import com.example.fake_store.data.network.CurrencyRateDTO
import com.example.fake_store.data.network.FakeStoreApi
import com.example.fake_store.data.network.UserDTO
import com.example.fake_store.data.network.toDomainProduct
import com.example.fake_store.data.network.toDtoProduct
import com.example.fake_store.data.storage.ProductsDao
import com.example.fake_store.data.storage.toDomain
import com.example.fake_store.data.storage.toEntity
import com.example.fake_store.domain.ProductInCartModel
import com.example.fake_store.domain.ProductModel
import com.example.fake_store.domain.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    @BaseFakeStoreApi private val api: FakeStoreApi,
    @BYNConverterApi private val currencyApi: CurrencyApi,
    private val dao: ProductsDao
) : ProductsRepository {

    override suspend fun getProducts(): List<ProductModel> {
        if (dao.getProductsCount() == 0) {
            val remoteProducts = api.getProducts().map { it.toDomainProduct() }
            remoteProducts.forEach { dao.insertProduct(it.toEntity()) }
            return remoteProducts
        } else {
            val localProducts = dao.getAllProducts().map { it.toDomain() }
            return localProducts
        }
    }

    override suspend fun getProduct(id: Int): ProductModel {
        return dao.getProductById(id).toDomain()
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

    override suspend fun updateProductPrice(
        product: ProductModel,
        newPrice: Double
    ) {
        withContext(Dispatchers.IO) {
            val updatedProduct = product.copy(price = newPrice)
            api.updateProduct(updatedProduct.id, updatedProduct.toDtoProduct())
            dao.updateProduct(updatedProduct.toEntity())
        }
    }

    override suspend fun getCurrencyRate(currencyId: Int): CurrencyRateDTO {
        return currencyApi.getCurrencyRate(currencyId)
    }

}