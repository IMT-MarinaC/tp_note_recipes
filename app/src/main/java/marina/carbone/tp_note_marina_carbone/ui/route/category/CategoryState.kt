package marina.carbone.tp_note_marina_carbone.ui.route.category

import marina.carbone.tp_note_marina_carbone.domain.model.MealPreview

data class CategoryState(
    val searchText: String = "",
    val sortType: SortType = SortType.ALPHABETICAL_ASC,
    val meals: List<MealPreview> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
