package com.letsjustunfold.productdisplay.data

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a configurable option for a product, such as "color" or "size".
 * It contains a list of specific attributes (e.g., individual colors or sizes).
 */
data class ConfigurableOption(
    @SerializedName("attribute_code")
    val attributeCode: String,
    @SerializedName("attribute_id")
    val attributeId: Int,
    val attributes: List<Attribute>,
    val type: String
)