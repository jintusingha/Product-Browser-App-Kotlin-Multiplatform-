package org.example.project.domain.usecase

import org.example.project.domain.model.Product
import org.example.project.domain.repository.ProductRepository

class GetProductUseCase(
    private val repository: ProductRepository,
) {
    suspend operator fun invoke(): List<Product> = repository.getProducts()
}
