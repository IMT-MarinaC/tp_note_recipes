package marina.carbone.tp_note_marina_carbone.domain.repository

import kotlinx.coroutines.flow.Flow
import marina.carbone.tp_note_marina_carbone.domain.model.Meal
import marina.carbone.tp_note_marina_carbone.domain.model.MealPreview

interface MealRepository {


    suspend fun getAllMealsFromCategory(categoryName: String): Flow<Result<List<MealPreview>>>

    suspend fun searchMeals(meal: String): Flow<Result<List<Meal>>>

}