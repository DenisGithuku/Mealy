package com.denisgithuku.mealy.domain.use_cases

import com.denisgithuku.mealy.common.Resource
import com.denisgithuku.mealy.data.remote.dto.toMealPreparation
import com.denisgithuku.mealy.domain.model.Category
import com.denisgithuku.mealy.domain.model.MealPreparation
import com.denisgithuku.mealy.domain.repositories.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealPreparationDetails @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(mealId: String): Flow<Resource<List<MealPreparation>>> = flow {
        try {
            emit(Resource.Loading<List<MealPreparation>>())
            val mealPrepDetails = repository.getMealPreparationDetails(mealId).meals.map { it.toMealPreparation() }
            emit(Resource.Success<List<MealPreparation>>(mealPrepDetails))
        }catch (e: HttpException) {
            emit(Resource.Error<List<MealPreparation>>(error = e.localizedMessage ?: "Network error"))
        }catch(e: IOException){
            emit(Resource.Error<List<MealPreparation>>(error = e.message ?: "An unexpected error occurred"))
        }
    }.flowOn(Dispatchers.IO)
}
