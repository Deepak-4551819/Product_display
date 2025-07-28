package com.letsjustunfold.productdisplay.data

/**
 * Top-level data class representing the API response for a single product.
 * It encapsulates the actual product data along with status and message.
 */

data class Product(
    val `data`: Data,
    val message: String,
    val status: Int
)