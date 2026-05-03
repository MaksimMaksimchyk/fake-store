package com.example.fake_store.data.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): List<ProductDTO>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): ProductDTO

    @POST("carts")
    suspend fun createCart(@Body cartDTO: CartDTO): CartDTO

    @POST("auth/login")
    suspend fun createToken(@Body user: UserDTO): TokenDTO

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Body product: ProductDTO
    ): ProductDTO


}