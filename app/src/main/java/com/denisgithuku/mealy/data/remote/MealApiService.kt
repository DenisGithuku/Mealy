package com.denisgithuku.mealy.data.remote

import com.denisgithuku.mealy.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("categories.php")
    suspend fun getAllMealCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsInCategory(@Query("c") category: String): MealInCategoryResponse

    @GET("lookup.php")
    suspend fun getMealPreparationDetails(@Query("i") mealId: String): MealPreparationResponse
}
