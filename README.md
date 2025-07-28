# Product Detail Page – Android Application

A modern, feature-rich Product Detail Page for an e-commerce app, built with Kotlin and following clean MVVM architecture. It demonstrates best practices in UI/UX, robust data handling, modular code design, and scalability.

---

## 🚀 Features

- **Dynamic Product Display**  
  Fetches and displays product name, price, and a detailed description from a REST API.

- **Image Gallery**  
  Swipeable image gallery using ViewPager2 to showcase multiple product images.

- **Image Navigation Arrows**  
  Left/right arrows appear for clear image navigation, only showing when relevant.

- **Expandable Product Information**  
  Collapsible product description card for cleaner, more readable content.

- **Loading & Error States**  
  Visual indicators during data fetch; user-friendly error messages for API/network failures.

- **Action Buttons**  
  "Add to Bag" and "Share" actions are fixed at the screen bottom for easy access.

- **Responsive UI**  
  Adapts gracefully to different screen sizes and orientations.

---

## 🏛 Architecture

Follows MVVM (Model-View-ViewModel) for a clean separation of concerns and high testability:


---

## 🛠 Technologies & Libraries

- **Kotlin**
- **AndroidX:** AppCompat, ConstraintLayout, Lifecycle (ViewModel, LiveData), Core KTX, Activity KTX.
- **Material Components for Android**
- **ViewPager2:** Swipeable image galleries.
- **Retrofit:** Networking & API consumption.
- **Gson:** JSON serialization/deserialization.
- **PhotoView** (chrisbanes/PhotoView): Advanced image zoom and pan in gallery.
- **Coil:** Fast image loading (used in gallery adapter).
- **Kotlin Coroutines:** Async/background thread management.

---

## 📦 Potential Enhancements

- **Image Gallery Indicators:** Dots or indicators showing the number/position of gallery images.
- **Coil Placeholders:** Better loading placeholders/animations for images within the ViewPager2.
- **Add to Bag Logic:** Full integration with a shopping cart backend.
- **Share Functionality:** Native Android share intent with product details.
- **Testing:** Unit tests for ViewModel/Repository and UI tests for key flows.
- **Dependency Injection:** Use Hilt or Koin for scalable DI setup.
- **Theming:** Custom branding via Material theming.
- **Product Quantity Selector:** UI and logic for quantity selection.


## 📸 Screenshots

| Product Display                                                                                         | Zoom Image                                                                                            | Error Massage                                                                                                   | 
| ----------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| ![Product Display](https://github.com/Deepak-4551819/Product_display/blob/6e311f7257a5172ce7eb72cd7d61efce6ddb8bc5/Screenshot/ProductDisplayScreen.jpg) | ![Zoom Image](https://github.com/Deepak-4551819/Product_display/blob/6e311f7257a5172ce7eb72cd7d61efce6ddb8bc5/Screenshot/ZoomImage.jpg)| ![Error Massage](https://github.com/Deepak-4551819/Product_display/blob/6e311f7257a5172ce7eb72cd7d61efce6ddb8bc5/Screenshot/Error%20massage.jpg) |
