package com.letsjustunfold.productdisplay.data

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a review for a product.
 */
data class Review(
    @SerializedName("rating_count")
    val ratingCount: Int,
    @SerializedName("total_review")
    val totalReview: Int
)