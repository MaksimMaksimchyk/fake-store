package com.example.fake_store.dagger2

import android.content.Context
import androidx.room.Room
import com.example.fake_store.data.storage.AppDatabase
import com.example.fake_store.data.storage.ProductsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
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
