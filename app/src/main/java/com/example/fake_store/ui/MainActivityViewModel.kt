package com.example.fake_store.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fake_store.data.storage.AuthManager
import com.example.fake_store.data.network.CartDTO
import com.example.fake_store.data.network.toDtoProduct
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
class MainActivityViewModel @Inject constructor(val productsInteractor: ProductsInteractor) :
    ViewModel() {

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

    private val _currentToken = MutableStateFlow<String>("")
    val currentToken = _currentToken.asStateFlow()


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
                productsInteractor.createCart(CartDTO(0, 0, listOf(product.toDtoProduct())))
            }
            _cartProducts.value.addAll(productsInteractor.getProductsFromCart())
        }
    }

    fun createToken(context: Context, username: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _currentToken.value = productsInteractor.createToken(username, password)
            }
            AuthManager.saveToken(context, _currentToken.value)
        }
    }

    fun changeToken(token: String) {
        _currentToken.value = token
    }


}