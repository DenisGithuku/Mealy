package com.denisgithuku.mealy.di

import com.denisgithuku.mealy.common.Constants
import com.denisgithuku.mealy.data.remote.MealApiService
import com.denisgithuku.mealy.data.repositories.DefaultMealRepositoryImpl
import com.denisgithuku.mealy.domain.repositories.MealRepository
import com.denisgithuku.mealy.domain.use_cases.GetAllMealCategories
import com.denisgithuku.mealy.domain.use_cases.GetMealPreparationDetails
import com.denisgithuku.mealy.domain.use_cases.GetMealsInCategory
import com.denisgithuku.mealy.domain.use_cases.MealUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): MealApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(MealApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMealRepository(mealApiService: MealApiService): MealRepository {
        return DefaultMealRepositoryImpl(
            mealApiService
        )
    }

    @Provides
    @Singleton
    fun provideMealUseCases(repository: MealRepository): MealUseCases {
        return MealUseCases(
            getAllMealCategories = GetAllMealCategories(repository),
            getMealPreparationDetails = GetMealPreparationDetails(repository),
            getMealsInCategory = GetMealsInCategory(repository)
        )
    }
}
