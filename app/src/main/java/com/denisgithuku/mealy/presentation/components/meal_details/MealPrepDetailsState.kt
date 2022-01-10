package com.denisgithuku.mealy.presentation.components.meal_details

import com.denisgithuku.mealy.domain.model.MealPreparation

data class MealPrepDetailsState(
    val isLoading: Boolean = false,
    val mealDetails: List<MealPreparation> = emptyList(),
    val mealInstructions: List<String> = emptyList(),
    val error: String = ""
)
