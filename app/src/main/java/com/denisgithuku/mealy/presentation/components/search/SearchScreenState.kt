package com.denisgithuku.mealy.presentation.components.search

import com.denisgithuku.mealy.domain.model.MealInSearch
import com.denisgithuku.mealy.domain.model.MealPreparation

data class SearchScreenState(
    val isLoading: Boolean = false,
    val meals: List<MealInSearch> = emptyList(),
    val error: String = ""
)
