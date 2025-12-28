package com.example.fake_store.data.storage

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart_items WHERE productId = :id")
    suspend fun getCartItemById(id: Int): CartItem?

    @Transaction
    suspend fun addOrUpdate(product: Product) {
        insertProduct(product)
        val existing = getCartItemById(product.productId)

        if (existing != null) {
            insertCartItem(existing.copy(quantity = existing.quantity + 1))
        } else {
            insertCartItem(CartItem(productId = product.productId, quantity = 1))
        }
    }

    @Transaction
    @Query("""
    SELECT * FROM products 
    WHERE productId IN (SELECT productId FROM cart_items)
""")
    fun getProductsWithCart(): List<ProductInCart>

    @Query("DELETE FROM cart_items WHERE productId = :id")
    suspend fun deleteCartItemById(id: Int)

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getProductsCount(): Int

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT * FROM products WHERE productId = :id")
    suspend fun getProductById(id: Int): Product

    @Update
    suspend fun updateProduct(product: Product)

}