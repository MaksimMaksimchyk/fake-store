package com.example.fake_store.data.storage

import com.example.fake_store.domain.ProductInCartModel
import com.example.fake_store.domain.ProductModel

fun ProductModel.toEntity(): Product {
    return Product(
        productId = this.id,
        category = this.category,
        description = this.description,
        image = this.image,
        price = this.price,
        title = this.title
    )
}

fun Product.toDomain(): ProductModel {
    return ProductModel(
        category = this.category,
        description = this.description,
        id = this.productId,
        image = this.image,
        price = this.price,
        title = this.title
    )
}

fun ProductInCart.toDomain(): ProductInCartModel {
    return ProductInCartModel(
        product = this.product.toDomain(),
        quantity = this.cartItem?.quantity ?: 0
    )
}