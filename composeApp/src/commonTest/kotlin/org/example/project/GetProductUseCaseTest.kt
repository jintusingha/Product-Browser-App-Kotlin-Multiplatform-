package org.example.project

import org.example.project.domain.model.Product
import org.example.project.domain.repository.ProductRepository
import org.example.project.domain.usecase.GetProductUseCase
import kotlin.test.Test
import kotlin.test.assertEquals

class FakeProductRepository : ProductRepository {
    override suspend fun getProducts(): List<Product> =
        listOf(
            Product(
                id = 1,
                title = "Test Product",
                description = "Test Description",
                price = 9.99,
                brand = "Test Brand",
                rating = 4.5,
                thumbnail = "https://example.com/image.jpg",
                category = "test-category",
            ),
        )

    override suspend fun searchProducts(query: String): List<Product> = emptyList()
}

class GetProductUseCaseTest {
    private val fakeRepository = FakeProductRepository()
    private val useCase = GetProductUseCase(fakeRepository)

    @Test
    fun `getProducts returns list of products`() =
        kotlinx.coroutines.test.runTest {
            val result = useCase()
            assertEquals(1, result.size)
            assertEquals("Test Product", result[0].title)
            assertEquals(9.99, result[0].price)
        }
}
