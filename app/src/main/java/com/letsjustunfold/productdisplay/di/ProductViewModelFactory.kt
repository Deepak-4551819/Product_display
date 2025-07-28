package com.letsjustunfold.productdisplay.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.letsjustunfold.ProductViewModel
import com.letsjustunfold.productdisplay.ProductRepository

/**
 * Factory class for creating instances of [ProductViewModel].
 * This is necessary for injecting dependencies (like [ProductRepository]) into a ViewModel,
 * allowing for proper testing and separation of concerns.
 *
 * @param repository The [ProductRepository] instance that the ViewModel will use to fetch data.
 */
class ProductViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    /**
     * Creates a new instance of the given `ViewModel` class.
     * @param modelClass The `Class` of the `ViewModel` to create.
     * @return A new `ViewModel` instance.
     * @throws IllegalArgumentException if an unknown `ViewModel` class is requested.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}