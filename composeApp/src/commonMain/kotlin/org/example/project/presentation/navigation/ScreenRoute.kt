package org.example.project.presentation.navigation

sealed class ScreenRoute(
    val route: String,
) {
    object ProductList : ScreenRoute("product_list_route")

    object ProductDetail : ScreenRoute(
        "product_detail_route/{productId}/{title}/{description}/{price}/{brand}/{rating}/{thumbnail}/{category}",
    ) {
        fun createRoute(
            productId: Int,
            title: String,
            description: String,
            price: Double,
            brand: String?,
            rating: Double,
            thumbnail: String,
            category: String?,
        ): String = "product_detail_route/$productId/$title/$description/$price/${brand ?: "null"}/$rating/$thumbnail/${category ?: "null"}"
    }
}
