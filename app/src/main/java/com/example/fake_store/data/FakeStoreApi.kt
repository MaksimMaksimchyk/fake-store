package com.example.fake_store.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): List<ProductDTO>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): ProductDTO

    @POST("carts")
    suspend fun createCart(@Body cartDTO: CartDTO): CartDTO

}