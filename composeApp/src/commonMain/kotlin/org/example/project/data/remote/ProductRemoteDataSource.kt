package org.example.project.data.remote

import org.example.project.data.dto.ProductResponseDto

interface ProductRemoteDataSource {
    suspend fun getProducts(): ProductResponseDto

    suspend fun searchProducts(query: String): ProductResponseDto
}
