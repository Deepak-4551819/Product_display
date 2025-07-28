package com.letsjustunfold

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsjustunfold.productdisplay.data.Data
import com.letsjustunfold.productdisplay.ProductRepository
import com.letsjustunfold.productdisplay.data.Result
import kotlinx.coroutines.launch


class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    // LiveData to hold the product details (which is now your 'Data' class)
    private val _product = MutableLiveData<Data?>() // <--- CHANGE THIS: Type is now 'Data'
    val product: LiveData<Data?> = _product

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun fetchProductDetails(
        productId: String,
        customerId: String,
        language: String,
        store: String
    ) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            when (val result = repository.getProductDetails(productId, customerId, language, store)) {
                is Result.Success -> {
                    _product.value = result.data // result.data now directly contains your 'Data' object
                    _isLoading.value = false
                }
                is Result.Error -> {
                    _errorMessage.value = result.message ?: "Failed to load product details."
                    _isLoading.value = false
                    _product.value = null
                }
                Result.Loading -> {}
            }
        }
    }
}