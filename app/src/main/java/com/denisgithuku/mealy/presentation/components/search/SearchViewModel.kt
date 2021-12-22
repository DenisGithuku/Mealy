package com.denisgithuku.mealy.presentation.components.search

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
class SearchViewModel @Inject constructor(
    private val useCases: MealUseCases
): ViewModel() {

    private var _state = mutableStateOf(SearchScreenState())
    val state: State<SearchScreenState> = _state

    fun searchMeal(mealName: String) {
        viewModelScope.launch {
            useCases.searchMeal(mealName).collect { result ->
                when(result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            meals = result.data ?: emptyList()
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
