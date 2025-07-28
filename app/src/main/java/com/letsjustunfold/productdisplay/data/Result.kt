package com.letsjustunfold.productdisplay.data

/**
 * A sealed class to represent the different states of an asynchronous operation (e.g., API call).
 * This provides a clear and safe way to handle success, error, and loading states.
 *
 * @param T The type of data on successful completion.
 */
sealed class Result<out T> {
    /**
     * Represents a successful outcome with data.
     * @param data The result data of type T.
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * Represents an error outcome with an exception and an optional message.
     * @param exception The exception that occurred.
     * @param message An optional, user-friendly error message.
     */
    data class Error(val exception: Exception, val message: String? = null) : Result<Nothing>()

    /**
     * Represents a loading state, indicating that an operation is in progress.
     */
    object Loading : Result<Nothing>()
}