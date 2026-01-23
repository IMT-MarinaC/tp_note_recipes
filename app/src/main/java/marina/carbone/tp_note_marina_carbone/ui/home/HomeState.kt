package marina.carbone.tp_note_marina_carbone.ui.home

import marina.carbone.tp_note_marina_carbone.domain.model.Category

data class HomeState(
    val searchText: String? = null,
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
