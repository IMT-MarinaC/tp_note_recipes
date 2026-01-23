package marina.carbone.tp_note_marina_carbone.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import marina.carbone.tp_note_marina_carbone.data.network.ApiClient
import marina.carbone.tp_note_marina_carbone.data.network.ApiService
import marina.carbone.tp_note_marina_carbone.data.network.toDomain
import marina.carbone.tp_note_marina_carbone.domain.model.Category
import marina.carbone.tp_note_marina_carbone.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val apiService: ApiService = ApiClient.create()
) : CategoryRepository {

    override suspend fun getAllCategories(): Flow<Result<List<Category>>> =
        flow {
            val response = apiService.getAllCategories()
            emit(Result.success(response.toDomain()))
        }.catch { throwable ->
            emit(Result.failure(throwable))
        }
}