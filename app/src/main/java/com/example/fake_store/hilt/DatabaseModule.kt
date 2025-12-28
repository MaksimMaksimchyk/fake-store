package com.example.fake_store.hilt

import android.content.Context
import androidx.room.Room
import com.example.fake_store.data.storage.AppDatabase
import com.example.fake_store.data.storage.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "shop_database"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideProductsDao(db: AppDatabase): ProductsDao {
        return db.productsDao()
    }
}
