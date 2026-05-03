package com.example.fake_store.data.storage

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "products")
data class Product(
    @PrimaryKey val productId: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val title: String
)

@Entity(
    tableName = "cart_items",
    foreignKeys = [ForeignKey(
        entity = Product::class,
        parentColumns = ["productId"],
        childColumns = ["productId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class CartItem(
    @PrimaryKey val productId: Int,
    val quantity: Int
)

data class ProductInCart(
    @Embedded val product: Product,
    @Relation(parentColumn = "productId", entityColumn = "productId")
    val cartItem: CartItem?
)

