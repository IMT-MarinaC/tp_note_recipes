package marina.carbone.tp_note_marina_carbone.data.network

import marina.carbone.tp_note_marina_carbone.data.network.dot.response.CategoryListResponse
import marina.carbone.tp_note_marina_carbone.data.network.dot.response.MealListByCategoryResponse
import marina.carbone.tp_note_marina_carbone.data.network.dot.response.MealListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("categories.php")
    suspend fun getAllCategories(): CategoryListResponse

    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealListResponse

    @GET("filter.php")
    suspend fun searchCategory(@Query("c") query: String): MealListByCategoryResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") query: String): MealListResponse
}