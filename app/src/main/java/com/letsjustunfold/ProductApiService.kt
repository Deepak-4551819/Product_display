package com.letsjustunfold

import com.letsjustunfold.productdisplay.data.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface defining the API endpoints for product-related operations.
 */
interface ProductApiService {
    /**
     * Fetches detailed information for a specific product.
     *
     * @param productId The unique identifier of the product.
     * @param customerId The unique identifier of the customer.
     * @param language The desired language for the product details (e.g., "en").
     * @param store The store code (e.g., "KWD").
     * @return A Retrofit [Response] object containing the [Product] data.
     */
    @GET("rest/V1/productdetails/{productId}/{customerId}")
    suspend fun getProductDetails(
        @Path("productId") productId: String,
        @Path("customerId") customerId: String,
        @Query("lang") language: String,
        @Query("store") store: String
    ): Response<Product>
}