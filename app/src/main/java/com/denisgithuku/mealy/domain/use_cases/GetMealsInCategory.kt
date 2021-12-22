package com.denisgithuku.mealy.domain.use_cases

import com.denisgithuku.mealy.common.Resource
import com.denisgithuku.mealy.data.remote.dto.toMealInCategory
import com.denisgithuku.mealy.domain.model.Category
import com.denisgithuku.mealy.domain.model.MealInCategory
import com.denisgithuku.mealy.domain.repositories.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMealsInCategory @Inject constructor(
    private val repository: MealRepository)
{
    suspend operator fun invoke(mealCategory: String): Flow<Resource<List<MealInCategory>>> = flow {
        try {
            emit(Resource.Loading<List<MealInCategory>>())
            val mealsInCategory = repository.getMealsInCategory(mealCategory).meals.map { it.toMealInCategory() }
            emit(Resource.Success<List<MealInCategory>>(mealsInCategory))
        } catch (e: Exception) {
            emit(Resource.Error<List<MealInCategory>>(error = e.message ?: "An unexpected error occurred"))
        }
    }.flowOn(Dispatchers.IO)
}
