package com.example.fake_store.data

import com.example.fake_store.domain.ProductModel

fun ProductDTO.toDomainProduct(): ProductModel {
    return ProductModel(this.category, this.description, this.id, this.image, this.price, this.title)
}