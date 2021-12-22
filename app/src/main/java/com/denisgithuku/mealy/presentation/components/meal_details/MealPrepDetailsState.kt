package com.denisgithuku.mealy.presentation.components.meal_details

import com.denisgithuku.mealy.domain.model.MealPreparation

data class MealPrepDetailsState(
    val isLoading: Boolean = false,
    val mealDetails: List<MealPreparation> = emptyList(),
    val error: String = ""
)
