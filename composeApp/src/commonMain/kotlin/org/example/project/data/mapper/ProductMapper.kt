package org.example.project.data.mapper

import org.example.project.data.dto.ProductDto
import org.example.project.domain.model.Product

fun ProductDto.toDomain(): Product =
    Product(
        id = id,
        title = title,
        description = description,
        price = price,
        brand = brand,
        rating = rating,
        thumbnail = thumbnail,
    )
