package com.denisgithuku.mealy.data.remote.dto

import com.denisgithuku.mealy.domain.model.MealInCategory

data class MealInCategoryDto(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

fun MealInCategoryDto.toMealInCategory(): MealInCategory {
    return MealInCategory(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb
    )
}
