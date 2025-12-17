package com.example.fake_store.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor() {
    var currentCartDTO = CartDTO(0, 0, emptyList())
}