package org.example.project.data.repository

import org.example.project.data.mapper.toDomain
import org.example.project.data.remote.ProductRemoteDataSource
import org.example.project.domain.model.Product
import org.example.project.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val remoteDataSource: ProductRemoteDataSource,
) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        val response = remoteDataSource.getProducts()
        return response.products.map { it.toDomain() }
    }

    override suspend fun searchProducts(query: String): List<Product> {
        val response = remoteDataSource.searchProducts(query)
        return response.products.map { it.toDomain() }
    }
}
