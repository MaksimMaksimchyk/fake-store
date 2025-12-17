package com.example.fake_store.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fake_store.data.CartDTO
import com.example.fake_store.data.ProductsRepositoryImpl
import com.example.fake_store.data.RetrofitClient
import com.example.fake_store.data.toDtoProduct
import com.example.fake_store.domain.ProductModel
import com.example.fake_store.domain.ProductsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(val productsInteractor: ProductsInteractor) : ViewModel() {

    private val _allProducts = MutableStateFlow<List<ProductModel>>(emptyList())
    val allProducts = _allProducts.asStateFlow()

    private val _cartProducts = MutableStateFlow<MutableList<ProductModel>>(mutableListOf())
    val cartProducts = _cartProducts.asStateFlow()
    private val _currentDetailsProduct = MutableStateFlow<ProductModel>(
        ProductModel(
            category = "",
            description = "",
            id = 0,
            image = "",
            price = 0.0,
            title = ""
        )
    )
    val currentDetailsProduct = _currentDetailsProduct.asStateFlow()

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

    fun changeCurrentDetailsProduct(product: ProductModel) {
        _currentDetailsProduct.value = product
    }

    fun addToCart(product: ProductModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                productsInteractor.createCart(CartDTO(666, 666, listOf(product.toDtoProduct())))
            }
            _cartProducts.value.addAll(productsInteractor.getProductsFromCart())
        }
    }

}