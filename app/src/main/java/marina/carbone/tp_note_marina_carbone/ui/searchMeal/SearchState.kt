package marina.carbone.tp_note_marina_carbone.ui.searchMeal

import marina.carbone.tp_note_marina_carbone.domain.model.Meal

data class SearchState(
    val searchText: String? = null,
    val meals: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
