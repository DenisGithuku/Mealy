package com.denisgithuku.mealy.domain.use_cases

import com.denisgithuku.mealy.common.Resource
import com.denisgithuku.mealy.data.remote.dto.toMealInSearch
import com.denisgithuku.mealy.data.remote.dto.toMealPreparation
import com.denisgithuku.mealy.domain.model.MealInSearch
import com.denisgithuku.mealy.domain.model.MealPreparation
import com.denisgithuku.mealy.domain.repositories.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchMeal @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(query: String): Flow<Resource<List<MealInSearch>>> = flow {
        try {
            emit(Resource.Loading<List<MealInSearch>>())
            val meals = repository.searchMealByName(query).meals.map { it.toMealInSearch() }
            emit(Resource.Success<List<MealInSearch>>(meals))
        }catch (e: HttpException) {
            emit(Resource.Error<List<MealInSearch>>(error = e.localizedMessage ?: "Couldn't reach server. Please retry"))
        }catch (e: IOException) {
            emit(Resource.Error<List<MealInSearch>>(error = e.message ?: "An unexpected error occurred"))
        }
    }.flowOn(Dispatchers.IO)
}
