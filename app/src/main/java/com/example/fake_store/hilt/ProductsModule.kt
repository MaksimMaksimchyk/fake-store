package com.example.fake_store.hilt

import com.example.fake_store.data.ProductsRepositoryImpl
import com.example.fake_store.domain.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ProductsModule {
    @Binds
    abstract fun bindProsuctsRepo(products: ProductsRepositoryImpl): ProductsRepository
}

