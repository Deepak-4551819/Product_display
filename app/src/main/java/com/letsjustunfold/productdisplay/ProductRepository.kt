package com.letsjustunfold.productdisplay

import com.letsjustunfold.ProductApiService
import com.letsjustunfold.productdisplay.data.Data
import retrofit2.HttpException
import java.io.IOException
import com.letsjustunfold.productdisplay.data.Result

/**
 * Repository class responsible for abstracting the data layer.
 * It provides a clean API for the ViewModel to interact with data sources (like network APIs).
 * It handles fetching product details and mapping API responses to a [Result] sealed class.
 *
 * @param apiService The [ProductApiService] interface for making network requests.
 */
class ProductRepository(
    private val apiService: ProductApiService
) {
    /**
     * Fetches product details from the API.
     * It wraps the network call in a `try-catch` block to handle various exceptions
     * and returns the result as a [Result] sealed class (Success, Error).
     *
     * @param productId The ID of the product to fetch.
     * @param customerId The ID of the current customer.
     * @param language The language for the product details.
     * @param store The store code for the product.
     * @return A [Result] object indicating success with [Data] or an error with an [Exception].
     */
    suspend fun getProductDetails(
        productId: String,
        customerId: String,
        language: String,
        store: String
    ): Result<Data> {
        try {
            val response = apiService.getProductDetails(productId, customerId, language, store)

            if (response.isSuccessful) {
                // Get the top-level Product object from the successful response.
                val apiProductResponse = response.body()
                // Check if the response body is not null and the API status is 200.
                if (apiProductResponse != null && apiProductResponse.status == 200) {
                    // On success, return the 'data' field which contains the actual product details.
                    return Result.Success(apiProductResponse.data)
                } else {
                    // If API returns an error status or null body, encapsulate it as an Error.
                    return Result.Error(
                        exception = Exception("API returned an error"),
                        message = apiProductResponse?.message ?: "Unknown API error"
                    )
                }
            } else {
                // If HTTP response itself is not successful (e.g., 404, 500), return an HTTP Error.
                return Result.Error(
                    exception = HttpException(response),
                    message = "HTTP Error: ${response.code()} - ${response.message()}"
                )
            }
        } catch (e: IOException) {
            // Catch network-related errors (e.g., no internet connection).
            return Result.Error(exception = e, message = "Network Error: ${e.message}")
        } catch (e: Exception) {
            // Catch any other unexpected errors.
            return Result.Error(exception = e, message = "An unexpected error occurred: ${e.message}")
        }
    }
}