package com.letsjustunfold.productdisplay

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge // For modern edge-to-edge display
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat // For handling system bars

/**
 * The main entry point of the application.
 * This activity primarily serves to navigate to the [ProductDetailActivity].
 * It sets up basic window insets and a button to view product details.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge display for a more immersive UI.
        enableEdgeToEdge()
        setContentView(R.layout.activity_main) // Set the layout for this activity.

        // Apply window insets to adjust padding for system bars (status bar, navigation bar).
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets // Return the insets to consume them.
        }

        // Find the button to navigate to the product detail screen.
        // Ensure that 'viewProductButton' ID exists in your 'activity_main.xml' layout.
        val viewProductButton: Button = findViewById(R.id.viewProductButton)

        // Set an OnClickListener for the button.
        viewProductButton.setOnClickListener {
            // Define a product ID to display. In a real app, this would be dynamic.
            val productIdToDisplay = "6701" // Example product ID

            // Create an Intent to start ProductDetailActivity.
            // Pass the product ID as an extra.
            val intent = Intent(this, ProductDetailActivity::class.java).apply {
                putExtra("PRODUCT_ID", productIdToDisplay)
            }
            startActivity(intent) // Start the activity.
        }
    }
}