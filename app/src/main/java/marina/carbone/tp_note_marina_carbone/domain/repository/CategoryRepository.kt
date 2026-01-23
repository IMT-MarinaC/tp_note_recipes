package marina.carbone.tp_note_marina_carbone.domain.repository

import kotlinx.coroutines.flow.Flow
import marina.carbone.tp_note_marina_carbone.domain.model.Category
import marina.carbone.tp_note_marina_carbone.domain.model.Meal

interface CategoryRepository {

    suspend fun getAllCategories(): Flow<Result<List<Category>>>
}