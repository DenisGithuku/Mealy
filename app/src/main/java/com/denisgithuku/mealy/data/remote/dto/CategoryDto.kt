package com.denisgithuku.mealy.data.remote.dto

import com.denisgithuku.mealy.domain.model.Category

data class CategoryDto(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)

fun CategoryDto.toCategory(): Category {
    return Category(
        idCategory = idCategory,
        strCategory = strCategory,
        strCategoryDescription = strCategoryDescription,
        strCategoryThumb = strCategoryThumb
    )
}
