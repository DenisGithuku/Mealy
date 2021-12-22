package com.denisgithuku.mealy.presentation.components.meal_details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisgithuku.mealy.common.Constants
import com.denisgithuku.mealy.common.Resource
import com.denisgithuku.mealy.domain.use_cases.MealUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val mealUseCases: MealUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _state = mutableStateOf(MealPrepDetailsState())
    val state: State<MealPrepDetailsState> = _state

    init {
        savedStateHandle.get<String>(Constants.paramMealId)?.let { mealId ->
            getMealPrepDetails(mealId)
        }
    }

    @InternalCoroutinesApi
    private fun getMealPrepDetails(mealId: String) {
        viewModelScope.launch {
            mealUseCases.getMealPreparationDetails(mealId).collect { result ->
                Log.d("meals", "${state.value.mealDetails}")
                when(result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            mealDetails = result.data ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.error ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }
    }
}
