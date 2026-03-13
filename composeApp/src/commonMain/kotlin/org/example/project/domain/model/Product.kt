package org.example.project.domain.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val brand: String?,
    val rating: Double,
    val thumbnail: String,
    val category: String? = null,
)
