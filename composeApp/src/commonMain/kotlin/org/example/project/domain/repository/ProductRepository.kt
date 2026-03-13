package org.example.project.domain.repository

import org.example.project.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>

    suspend fun searchProducts(query: String): List<Product>
}
