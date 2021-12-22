package com.denisgithuku.mealy.domain.repositories

import com.denisgithuku.mealy.data.remote.dto.*

interface MealRepository {

    suspend fun getAllMealCategories(): CategoryResponse

    suspend fun getMealsInCategory(category: String): MealInCategoryResponse

    suspend fun getMealPreparationDetails(mealId: String): MealPreparationResponse

    suspend fun searchMealByName(query: String): SearchMealResponse
}
