package org.example.project.presentation.navigation

sealed class ScreenRoute(
    val route: String,
) {
    object ProductList : ScreenRoute("product_list_route")

    object ProductDetail : ScreenRoute("product_detail_route/{productId}") {
        fun createRoute(productId: Int) = "product_detail_route/$productId"
    }
}
