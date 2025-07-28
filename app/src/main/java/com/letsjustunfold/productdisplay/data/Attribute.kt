package com.letsjustunfold.productdisplay.data

import com.google.gson.annotations.SerializedName

/**
 * Data class representing an individual attribute associated with a configurable product option.
 * This typically includes details like image URLs, color codes, and price variations.
 */
data class Attribute(
    @SerializedName("attribute_image_url")
    val attributeImageUrl: String,
    @SerializedName("color_code")
    val colorCode: Any,
    val images: List<String>,
    @SerializedName("option_id")
    val optionId: String,
    val price: String,
    @SerializedName("swatch_url")
    val swatchUrl: String,
    val value: String
)