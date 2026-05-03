package com.example.fake_store.dagger2

import com.example.fake_store.data.ProductsRepositoryImpl
import com.example.fake_store.domain.ProductsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class ProductsModule {
    @Binds
    abstract fun bindProductsRepo(products: ProductsRepositoryImpl): ProductsRepository
}

