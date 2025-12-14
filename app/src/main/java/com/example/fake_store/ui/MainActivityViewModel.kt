package com.example.fake_store.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fake_store.data.ProductsRepositoryImpl
import com.example.fake_store.domain.ProductModel
import com.example.fake_store.domain.ProductsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {

    private val _allProducts = MutableStateFlow<List<ProductModel>>(emptyList())
    val allProducts = _allProducts.asStateFlow()

    private val productsInteractor: ProductsInteractor by lazy {
        val repository = ProductsRepositoryImpl()
        ProductsInteractor(repository)
    }

    init {
        loadProducts()
    }

    fun loadProducts() {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _allProducts.value = productsInteractor.getProducts()
            }
        }

    }


}