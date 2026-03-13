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
import org.example.project.presentation.viewmodel.ProductViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    val viewModel: ProductViewModel = koinViewModel()
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.ProductList.route,
    ) {
        composable(ScreenRoute.ProductList.route) {
            ProductListScreen(
                viewModel = viewModel,
                onProductClick = { product ->
                    navController.navigate(
                        ScreenRoute.ProductDetail.createRoute(product.id),
                    )
                },
            )
        }

        composable(
            route = ScreenRoute.ProductDetail.route,
            arguments =
                listOf(
                    navArgument("productId") { type = NavType.IntType },
                ),
        ) { backStackEntry ->
            val productId = backStackEntry.arguments!!.getInt("productId")

            val product = viewModel.getProductById(productId)

            if (product != null) {
                ProductDetailScreen(
                    product = product,
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}
