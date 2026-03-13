package org.example.project.presentation.state

import org.example.project.domain.model.Product

sealed class ProductUiState {
    object Loading : ProductUiState()

    data class Success(
        val products: List<Product>,
    ) : ProductUiState()

    data class Error(
        val message: String,
    ) : ProductUiState()
}
