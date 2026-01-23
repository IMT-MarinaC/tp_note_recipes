package marina.carbone.tp_note_marina_carbone.data.network.dot.response

import com.google.gson.annotations.SerializedName

data class CategoryListResponse(
    @SerializedName("categories")
    val categories: List<CategoryResponse>
)