package marina.carbone.tp_note_marina_carbone.ui.route.meal

import marina.carbone.tp_note_marina_carbone.domain.model.Meal

data class MealState(
    val meal: Meal? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
