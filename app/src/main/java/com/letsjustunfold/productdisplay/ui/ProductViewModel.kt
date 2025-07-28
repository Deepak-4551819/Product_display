package com.letsjustunfold.productdisplay.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letsjustunfold.productdisplay.ProductRepository
import com.letsjustunfold.productdisplay.data.Data
import com.letsjustunfold.productdisplay.data.Result
import kotlinx.coroutines.launch

/**
 * ViewModel for the product detail screen.
 * It is responsible for fetching and holding product data,
 * and exposing it to the UI via LiveData, independent of UI lifecycle.
 *
 * @param repository The [ProductRepository] used to fetch product details from the data source.
 */
class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    // LiveData to hold the main product details (Data object).
    // It's a MutableLiveData internally for modification and exposed as LiveData for observation.
    private val _product = MutableLiveData<Data?>()
    val product: LiveData<Data?> = _product

    // LiveData to track the loading state of the product details.
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // LiveData to hold any error messages that occur during data fetching.
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    /**
     * Initiates the fetching of product details.
     * Updates loading state, clears previous errors, and launches a coroutine to fetch data.
     * Handles success, error, and loading states from the repository.
     *
     * @param productId The ID of the product to fetch.
     * @param customerId The ID of the current customer.
     * @param language The language for the product details.
     * @param store The store code for the product.
     */
    fun fetchProductDetails(
        productId: String,
        customerId: String,
        language: String,
        store: String
    ) {
        _isLoading.value = true // Set loading to true before starting fetch
        _errorMessage.value = null // Clear any previous error messages

        viewModelScope.launch {
            when (val result = repository.getProductDetails(productId, customerId, language, store)) {
                is Result.Success -> {
                    _product.value = result.data // Update product data on success
                    _isLoading.value = false      // Set loading to false
                }
                is Result.Error -> {
                    // Update error message and set loading to false on error
                    _errorMessage.value = result.message ?: "Failed to load product details."
                    _isLoading.value = false
                    _product.value = null // Clear product data on error
                }
                Result.Loading -> {
                    // This state is primarily handled by the initial _isLoading.value = true
                    // and might be used for intermediate loading updates if the repository also emits it.
                }
            }
        }
    }
}