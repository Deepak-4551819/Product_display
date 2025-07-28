package com.letsjustunfold.productdisplay

// import coil.load // Although not directly used for the main image, useful for future images
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.letsjustunfold.ProductViewModel
import com.letsjustunfold.productdisplay.databinding.ActivityProductDetailBinding
import com.letsjustunfold.productdisplay.di.ProductViewModelFactory
import com.letsjustunfold.productdisplay.network.RetrofitClient
import com.letsjustunfold.productdisplay.ui.productdetail.ProductImageAdapter

/**
 * Activity for displaying detailed information about a single product.
 * It fetches product data using a ViewModel and updates the UI accordingly.
 * This activity handles product image display, text details, and expandable sections.
 */
class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    // Initialize ProductRepository and ViewModel using Hilt/ViewModelFactory pattern.
    // ProductRepository is created with the Retrofit API service.
    private val productRepository = ProductRepository(RetrofitClient.apiService)
    private val viewModel: ProductViewModel by viewModels { ProductViewModelFactory(productRepository) }

    // Variable to hold the total number of images for arrow visibility logic
    private var totalImageCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding for safer UI interactions.
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe the product LiveData from the ViewModel.
        // UI elements are updated when product data is successfully fetched.
        viewModel.product.observe(this, Observer { productData ->
            productData?.let { product ->
                // Update basic product details
                binding.productNameTextView.text = product.name
                binding.productPriceTextView.text = String.format(getString(R.string.product_price), product.finalPrice)

                // Setup ViewPager2 for displaying product images.
                // The ProductImageAdapter will handle loading images into each page.
                val imageUrls = product.images
                totalImageCount = imageUrls.size
                Log.d("ProductDetailActivity", "Total images: $totalImageCount") // Log total images

                if (imageUrls.isNotEmpty()) {
                    val adapter = ProductImageAdapter(imageUrls)
                    binding.productImageViewPager.adapter = adapter

                    binding.productImageViewPager.registerOnPageChangeCallback(object :
                        ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)
                            updateNavigationArrowsVisibility(position)
                            Log.d("ProductDetailActivity", "Page selected: $position") // Log page changes
                        }
                    })

                    // Set initial arrow visibility
                    updateNavigationArrowsVisibility(binding.productImageViewPager.currentItem)

                } else {
                    // Hide the ViewPager if no images are available.
                    binding.productImageViewPager.visibility = View.GONE
                    // Hide arrows if no images
//                    binding.arrowLeftButton.visibility = View.GONE
//                    binding.arrowRightButton.visibility = View.GONE
                }

                // Load the product description, parsing HTML content.
                binding.productDescriptionTextView.text = HtmlCompat.fromHtml(
                    product.description,
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                )

                // Setup the expandable product information section.
                // This allows toggling the visibility of the product description.
                binding.productInfoExpandableHeader.setOnClickListener {
                    if (binding.productDescriptionTextView.isGone) {
                        // Expand: show description and rotate arrow up
                        binding.productDescriptionTextView.visibility = View.VISIBLE
                        binding.productInfoArrow.animate().rotation(180f).setDuration(200).start()
                        // Ensure the spacer is visible if it exists in the layout
                        binding.root.findViewById<View>(R.id.space)?.visibility = View.VISIBLE
                    } else {
                        // Collapse: hide description and rotate arrow down
                        binding.productDescriptionTextView.visibility = View.GONE
                        binding.productInfoArrow.animate().rotation(0f).setDuration(200).start()
                        // Hide the spacer if it exists
                        binding.root.findViewById<View>(R.id.space)?.visibility = View.GONE
                    }
                }

                // Show the content and hide loading/error indicators.
                binding.contentScrollView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.errorTextView.visibility = View.GONE
            }
        })

        // Observe the loading state from the ViewModel.
        // Show/hide ProgressBar based on loading status.
        viewModel.isLoading.observe(this, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            if (isLoading) {
                // Hide content and error when loading.
                binding.contentScrollView.visibility = View.GONE
                binding.errorTextView.visibility = View.GONE
            }
        })

        // Observe error messages from the ViewModel.
        // Display error message and hide other UI elements on error.
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                binding.errorTextView.text = it
                binding.errorTextView.visibility = View.VISIBLE

                // Hide content and progress bar on error.
                binding.contentScrollView.visibility = View.GONE
                binding.progressBar.visibility = View.GONE

                // Optionally show a Toast for immediate feedback.
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        // Get product ID from intent, with a default value for demonstration.
        val productId = intent.getStringExtra("PRODUCT_ID") ?: "6701"
        // Define other API parameters.
        val customerId = "253620"
        val language = "en"
        val store = "KWD"

        // Trigger the product details fetch.
        viewModel.fetchProductDetails(productId, customerId, language, store)

        // NEW: Set click listeners for navigation arrows
        binding.arrowLeftButton.setOnClickListener {
            val currentItem = binding.productImageViewPager.currentItem
            if (currentItem > 0) {
                binding.productImageViewPager.currentItem = currentItem - 1
                Log.d("ProductDetailActivity", "Left arrow clicked. Current item: $currentItem, Moving to: ${currentItem - 1}")
            }  else {
                Log.d("ProductDetailActivity", "Left arrow clicked. Already at first page ($currentItem). Cannot move left.")
            }
        }

        binding.arrowRightButton.setOnClickListener {
            val currentItem = binding.productImageViewPager.currentItem
            val lastPageIndex = totalImageCount - 1
            Log.d("ProductDetailActivity", "Right arrow clicked. Current item: $currentItem, Total images: $totalImageCount, Last index: $lastPageIndex")
            if (currentItem < totalImageCount - 1) {
                binding.productImageViewPager.currentItem = currentItem + 1
                Log.d("ProductDetailActivity", "Moving to: ${currentItem + 1}")
            } else {
                Log.d("ProductDetailActivity", "Right arrow clicked. Already at last page ($currentItem / $lastPageIndex). Cannot move right.")
            }
        }

        // Set click listeners for the action buttons at the bottom.
        binding.addToBagButton.setOnClickListener {
            Toast.makeText(this, "Add to bag clicked!", Toast.LENGTH_SHORT).show()
            // TODO: Implement actual add to bag logic here.
        }

        binding.shareButton.setOnClickListener {
            Toast.makeText(this, "Share clicked!", Toast.LENGTH_SHORT).show()
            // TODO: Implement actual share logic here (e.g., using Intent.ACTION_SEND).
        }
    }

        /**
         * Updates the visibility of the left and right navigation arrows based on the current page.
         * Hides the left arrow on the first page and the right arrow on the last page.
         * @param currentPage The currently displayed page index in the ViewPager2.
         */
        private fun updateNavigationArrowsVisibility(currentPage: Int) {

            if (totalImageCount <= 1) {
                // Hide both arrows if there's only one or no images
                binding.arrowLeftButton.visibility = View.GONE
                binding.arrowRightButton.visibility = View.GONE
            } else {
                // Show/hide left arrow
                binding.arrowLeftButton.visibility = if (currentPage == 0) View.GONE else View.VISIBLE
                // Show/hide right arrow
                binding.arrowRightButton.visibility = if (currentPage == totalImageCount - 1) View.GONE else View.VISIBLE
            }
    }
}