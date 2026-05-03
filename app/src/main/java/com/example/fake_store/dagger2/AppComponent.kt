package com.example.fake_store.dagger2

import android.content.Context
import com.example.fake_store.ui.MainActivity
import com.example.fake_store.ui.fragments.CatalogFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class, NetworkModule::class, ProductsModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: CatalogFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}