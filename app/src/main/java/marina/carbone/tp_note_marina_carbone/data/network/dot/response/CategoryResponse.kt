package marina.carbone.tp_note_marina_carbone.data.network.dot.response

import com.google.gson.annotations.SerializedName


data class CategoryResponse(
    val idCategory: String,

    @SerializedName("strCategory")
    val categoryName: String,

    @SerializedName("strCategoryThumb")
    val categoryThumb: String,

    @SerializedName("strCategoryDescription")
    val categoryDescription: String
)