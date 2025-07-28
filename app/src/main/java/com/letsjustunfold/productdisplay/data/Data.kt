package com.letsjustunfold.productdisplay.data

import com.google.gson.annotations.SerializedName
import com.letsjustunfold.productdisplay.data.Review

/**
 * Core data class representing the main product details received from the API.
 * This holds comprehensive information about a single product.
 */

data class Data(
    @SerializedName("about_the_brand")
    val aboutTheBrand: Any,
    @SerializedName("attribute_set_id")
    val attributeSetId: String,
    val brand: String,
    @SerializedName("brand_banner_url")
    val brandBannerUrl: String,
    @SerializedName("brand_name")
    val brandName: String,
    @SerializedName("bundle_options")
    val bundleOptions: List<Any>,
    @SerializedName("celebrity_id")
    val celebrityId: Int,
    @SerializedName("configurable_option")
    val configurableOption: List<ConfigurableOption>,
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    @SerializedName("final_price")
    val finalPrice: String,
    @SerializedName("has_options")
    val hasOptions: String,
    @SerializedName("how_to_use")
    val howToUse: Any,
    val id: String,
    val image: String,
    val images: List<String>,       // Main product image URL.
    @SerializedName("is_best_seller")
    val isBestSeller: Int,
    @SerializedName("is_following_brand")
    val isFollowingBrand: Int,
    @SerializedName("is_new")
    val isNew: Int,
    @SerializedName("is_return")
    val isReturn: Int,
    @SerializedName("is_salable")
    val isSalable: Boolean,
    @SerializedName("is_sale")
    val isSale: Int,
    @SerializedName("is_trending")
    val isTrending: Int,
    @SerializedName("key_ingredients")
    val keyIngredients: Any,
    val manufacturer: String?,
    @SerializedName("meta_description")
    val metaDescription: String,
    @SerializedName("meta_keyword")
    val metaKeyword: Any,
    @SerializedName("meta_title")
    val metaTitle: String,
    val name: String,
    val options: List<Any>,
    val price: String,
    val related: List<String>?,
    @SerializedName("remaining_qty")
    val remainingQty: Int,
    @SerializedName("returns_and_exchanges")
    val returnsAndExchanges: Any,
    val review: Review,
    @SerializedName("shipping_and_delivery")
    val shippingAndDelivery: Any,
    @SerializedName("short_description")
    val shortDescription: Any,
    @SerializedName("size_chart")
    val sizeChart: Any,
    val sku: String,
    val status: String,
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val upsell: List<Any>,
    @SerializedName("web_url")
    val webUrl: String,
    val weight: Any,
    @SerializedName("wishlist_item_id")
    val wishlistItemId: Int
)