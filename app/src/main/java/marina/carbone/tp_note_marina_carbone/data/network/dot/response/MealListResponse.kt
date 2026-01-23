package marina.carbone.tp_note_marina_carbone.data.network.dot.response

import com.google.gson.annotations.SerializedName

data class MealListResponse(
    @SerializedName("meals")
    val meals: List<MealResponse>
)

data class MealListByCategoryResponse(
    @SerializedName("meals")
    val meals: List<MealByCategoryResponse>?
)