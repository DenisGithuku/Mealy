package com.denisgithuku.mealy.domain.use_cases

data class MealUseCases(
    val getMealPreparationDetails: GetMealPreparationDetails,
    val getAllMealCategories: GetAllMealCategories,
    val getMealsInCategory: GetMealsInCategory
)
