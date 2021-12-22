package com.denisgithuku.mealy.data.repositories

import com.denisgithuku.mealy.data.remote.MealApiService
import com.denisgithuku.mealy.data.remote.dto.*
import com.denisgithuku.mealy.domain.repositories.MealRepository

class DefaultMealRepositoryImpl(
    private val mealApiService: MealApiService
): MealRepository {
    override suspend fun getAllMealCategories(): CategoryResponse {
        return mealApiService.getAllMealCategories()
    }

    override suspend fun getMealsInCategory(category: String): MealInCategoryResponse {
        return mealApiService.getMealsInCategory(category)
    }

    override suspend fun getMealPreparationDetails(mealId: String): MealPreparationResponse {
        return mealApiService.getMealPreparationDetails(mealId)
    }

    override suspend fun searchMealByName(query: String): SearchMealResponse {
        return mealApiService.searchMealByName(query)
    }
}
