package org.example.project.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val brand: String? = null,
    val rating: Double,
    val thumbnail: String,
    val category: String? = null,
)
