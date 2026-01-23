package marina.carbone.tp_note_marina_carbone.domain.model

data class Meal(
    val id: String,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbnail: String,
    val tags: List<String>,
    val youtubeUrl: String?,
    val ingredients: List<Ingredient>
)

data class MealPreview(
    val id: String,
    val name: String,
    val thumbnail: String
)

