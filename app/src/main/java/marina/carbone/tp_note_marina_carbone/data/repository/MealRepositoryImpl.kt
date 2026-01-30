package marina.carbone.tp_note_marina_carbone.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import marina.carbone.tp_note_marina_carbone.data.network.ApiClient
import marina.carbone.tp_note_marina_carbone.data.network.ApiService
import marina.carbone.tp_note_marina_carbone.data.network.toDomain
import marina.carbone.tp_note_marina_carbone.domain.model.Meal
import marina.carbone.tp_note_marina_carbone.domain.model.MealPreview
import marina.carbone.tp_note_marina_carbone.domain.repository.MealRepository

class MealRepositoryImpl(
    private val apiService: ApiService = ApiClient.create()
) : MealRepository {

    override suspend fun getAllMealsFromCategory(categoryName: String): Flow<Result<List<MealPreview>>> =
        flow {
            val response = apiService.searchCategory(categoryName)
            emit(Result.success(response.toDomain()))
        }.catch { throwable ->
            emit(Result.failure(throwable))
        }

    override suspend fun getMealById(mealId: String): Result<Meal> {
        return try {
            val response = apiService.getMealById(mealId)
            val meal = response.meals.firstOrNull()?.toDomain()
                ?: throw Exception("Meal introuvable")
            Result.success(meal)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun searchMeals(meal: String): Flow<Result<List<Meal>>> =
        flow {
            val response = apiService.searchMeals(meal)
            emit(Result.success(response.toDomain()))
        }.catch { throwable ->
            emit(Result.failure(throwable))
        }
}
