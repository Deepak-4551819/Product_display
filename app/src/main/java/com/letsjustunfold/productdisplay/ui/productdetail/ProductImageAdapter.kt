package com.letsjustunfold.productdisplay.ui.productdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load // For efficient image loading
import com.letsjustunfold.productdisplay.R
import com.github.chrisbanes.photoview.PhotoView // For zoomable images

/**
 * RecyclerView Adapter for displaying product images within a ViewPager2.
 * Each image is displayed using PhotoView to allow for zooming and panning.
 *
 * @param imageUrls List of URLs for the product images to be displayed.
 */
class ProductImageAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<ProductImageAdapter.ImageViewHolder>() {

    /**
     * ViewHolder for each image item in the RecyclerView.
     * It holds a reference to the PhotoView.
     */
    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // The PhotoView component for displaying images with zoom capabilities.
        val imageView: PhotoView = view.findViewById(R.id.imageViewProduct)
    }

    /**
     * Called when RecyclerView needs a new [ImageViewHolder] of the given type to represent an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        // Inflates the layout for a single product image item.
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_image, parent, false)
        return ImageViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method updates the contents of the [ImageViewHolder.itemView] to reflect the item at the given position.
     */
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageUrls[position]
        // Loads the image into the PhotoView using Coil, with placeholders for loading and error states.
        holder.imageView.load(imageUrl) {
            crossfade(true) // Enable crossfade animation
            placeholder(R.drawable.image_placeholder) // Drawable to show while loading
            error(R.drawable.ic_error) // Drawable to show if loading fails
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    override fun getItemCount(): Int = imageUrls.size
}