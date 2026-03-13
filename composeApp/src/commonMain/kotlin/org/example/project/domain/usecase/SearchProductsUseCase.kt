package org.example.project.domain.usecase

import org.example.project.domain.model.Product
import org.example.project.domain.repository.ProductRepository

class SearchProductsUseCase(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(query: String): List<Product> = repository.searchProducts(query)
}
