package org.example.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.example.project.domain.model.Product
import org.example.project.presentation.screens.ProductDetailScreen
import org.example.project.presentation.screens.ProductListScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.ProductList.route,
    ) {
        composable(ScreenRoute.ProductList.route) {
            ProductListScreen(
                onProductClick = { product ->
                    navController.navigate(
                        ScreenRoute.ProductDetail.createRoute(
                            productId = product.id,
                            title = product.title,
                            description = product.description,
                            price = product.price,
                            brand = product.brand,
                            rating = product.rating,
                            thumbnail = product.thumbnail,
                            category = product.category,
                        ),
                    )
                },
            )
        }

        composable(
            route = ScreenRoute.ProductDetail.route,
            arguments =
                listOf(
                    navArgument("productId") { type = NavType.IntType },
                    navArgument("title") { type = NavType.StringType },
                    navArgument("description") { type = NavType.StringType },
                    navArgument("price") { type = NavType.FloatType },
                    navArgument("brand") {
                        type = NavType.StringType
                        nullable = true
                    },
                    navArgument("rating") { type = NavType.FloatType },
                    navArgument("thumbnail") { type = NavType.StringType },
                    navArgument("category") {
                        type = NavType.StringType
                        nullable = true
                    },
                ),
        ) { backStackEntry ->
            val args = backStackEntry.arguments!!
            val product =
                Product(
                    id = args.getInt("productId"),
                    title = args.getString("title") ?: "",
                    description = args.getString("description") ?: "",
                    price = args.getFloat("price").toDouble(),
                    brand = args.getString("brand").takeIf { it != "null" },
                    rating = args.getFloat("rating").toDouble(),
                    thumbnail = args.getString("thumbnail") ?: "",
                    category = args.getString("category").takeIf { it != "null" },
                )
            ProductDetailScreen(
                product = product,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
