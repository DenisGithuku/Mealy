package com.denisgithuku.mealy.domain.use_cases

import com.denisgithuku.mealy.common.Resource
import com.denisgithuku.mealy.data.remote.dto.toCategory
import com.denisgithuku.mealy.data.repositories.DefaultMealRepositoryImpl
import com.denisgithuku.mealy.domain.model.Category
import com.denisgithuku.mealy.domain.repositories.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllMealCategories @Inject constructor(
    private val repository: MealRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<Category>>> = flow {
        try {
            emit(Resource.Loading<List<Category>>())
            val categories = repository.getAllMealCategories().categories.map { it.toCategory() }
            emit(Resource.Success<List<Category>>(categories))
        }catch (e: Exception) {
            emit(Resource.Error<List<Category>>(error = e.message ?: "An unexpected error happened"))
        }
    }.flowOn(Dispatchers.IO)
}
