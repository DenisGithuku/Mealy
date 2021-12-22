package com.denisgithuku.mealy.presentation.components.home

import com.denisgithuku.mealy.domain.model.Category
import com.denisgithuku.mealy.domain.model.MealInCategory

data class HomeScreenState(
    val isLoading: Boolean = false,
    val mealCategories: List<Category> = emptyList(),
    val mealInEachCategory: List<MealInCategory> = emptyList(),
    val error: String = ""
)
