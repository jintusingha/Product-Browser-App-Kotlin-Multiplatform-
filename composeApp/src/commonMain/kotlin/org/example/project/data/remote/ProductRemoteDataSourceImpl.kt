package org.example.project.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.project.data.dto.ProductResponseDto

class ProductRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : ProductRemoteDataSource {
    override suspend fun getProducts(): ProductResponseDto =
        httpClient
            .get("https://dummyjson.com/products")
            .body()

    override suspend fun searchProducts(query: String): ProductResponseDto =
        httpClient
            .get("https://dummyjson.com/products/search?q=$query")
            .body()
}
