package com.example.restaurantreview

data class PostReviewResponse(
    val customerReviews: List<CustomerReviewsItem>,
    val error: Boolean,
    val message: String
)
