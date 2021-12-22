package com.denisgithuku.mealy.presentation.components.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisgithuku.mealy.common.Resource
import com.denisgithuku.mealy.domain.use_cases.MealUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealUseCases: MealUseCases
) : ViewModel() {

    private var _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    private var _selectedMealCategory = mutableStateOf("Beef")
    val selectedMealCategory: State<String> = _selectedMealCategory


    init {
        getAllMealCategories()
        getMealsInCategory(_selectedMealCategory.value)
    }

    private fun getAllMealCategories() {
        viewModelScope.launch {
            mealUseCases.getAllMealCategories().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            mealCategories = result.data ?: emptyList()
                        )
                        println(result.data?.first()?.strCategory)
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
    fun getMealsInCategory(mealCategory: String) {
        viewModelScope.launch {
            mealUseCases.getMealsInCategory(mealCategory).collect { result ->
                when(result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            mealInEachCategory = result.data ?: emptyList()
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

    fun changeSelectedMealCategory(value: String) {
        _selectedMealCategory.value = value
    }

    fun retryFetchingData() {
        getAllMealCategories()
        getMealsInCategory(_selectedMealCategory.value)
    }
}
