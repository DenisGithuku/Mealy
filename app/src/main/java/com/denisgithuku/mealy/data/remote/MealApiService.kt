package com.denisgithuku.mealy.data.remote

import com.denisgithuku.mealy.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("api/json/v1/1/categories.php")
    suspend fun getAllMealCategories(): CategoryResponse

    @GET("api/json/v1/1/filter.php")
    suspend fun getMealsInCategory(@Query("c") category: String): MealInCategoryResponse

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealPreparationDetails(@Query("i") mealId: String): MealPreparationResponse

    @GET("api/json/v1/1/search.php")
    suspend fun searchMealByName(@Query("s") query: String): SearchMealResponse
}
