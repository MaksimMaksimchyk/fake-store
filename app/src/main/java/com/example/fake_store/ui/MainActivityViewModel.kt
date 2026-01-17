package com.example.fake_store.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fake_store.data.network.CartDTO
import com.example.fake_store.data.network.toDtoProduct
import com.example.fake_store.data.storage.AuthManager
import com.example.fake_store.domain.ProductInCartModel
import com.example.fake_store.domain.ProductModel
import com.example.fake_store.domain.ProductsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

class MainActivityViewModel @Inject constructor(val productsInteractor: ProductsInteractor) :
    ViewModel() {

    private val _allProducts = MutableStateFlow<List<ProductModel>>(emptyList())
    val allProducts = _allProducts.asStateFlow()

    private val _cartProducts = MutableStateFlow<List<ProductInCartModel>>(mutableListOf())
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
        loadCart()
    }

    fun loadProducts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _allProducts.value = productsInteractor.getProducts()
            }
        }
    }

    fun loadCart() {
        viewModelScope.launch {
            val updatedList = withContext(Dispatchers.IO) {
                productsInteractor.getProductsFromCart()
            }
            _cartProducts.value = updatedList
        }
    }

    fun changeCurrentDetailsProduct(product: ProductModel) {
        _currentDetailsProduct.value = product
    }

    fun changeProductPrice(newPrice: Double) {
        viewModelScope.launch {
            productsInteractor.updateProductPrice(currentDetailsProduct.value, newPrice)
            loadProducts()
            loadCart()
        }
    }

    fun addToCart(product: ProductModel) {
        viewModelScope.launch {
            val updatedList = withContext(Dispatchers.IO) {
                productsInteractor.createCart(CartDTO(0, 0, listOf(product.toDtoProduct())))
                productsInteractor.addProductToCart(product)
                productsInteractor.getProductsFromCart()
            }
            _cartProducts.value = updatedList
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

    fun removeFromCart(productId: Int) {
        viewModelScope.launch {
            productsInteractor.removeFromCart(productId)
            loadCart()
        }
    }
}

class MainActivityViewModelFactory @Inject constructor(
    private val myViewModelProvider: Provider<MainActivityViewModel>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return myViewModelProvider.get() as T
    }
}