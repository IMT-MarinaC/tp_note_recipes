package marina.carbone.tp_note_marina_carbone.ui.route.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import marina.carbone.tp_note_marina_carbone.data.repository.MealRepositoryImpl
import marina.carbone.tp_note_marina_carbone.domain.model.MealPreview
import marina.carbone.tp_note_marina_carbone.domain.repository.MealRepository

class CategoryViewModel(
    private val mealRepository: MealRepository = MealRepositoryImpl(),
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryState())
    val state: StateFlow<CategoryState> = _state.asStateFlow()

    fun sendUIEvent(event: CategoryUIEvent) {
        when (event) {

            is CategoryUIEvent.LoadMeals -> loadMealsByCategory(event.categoryName)

            is CategoryUIEvent.SearchChanged ->
                _state.update {
                    it.copy(searchText = event.text)
                }

            CategoryUIEvent.ToggleSort ->
                _state.update {
                    it.copy(
                        sortType = when (it.sortType) {
                            SortType.ALPHABETICAL_ASC ->
                                SortType.ALPHABETICAL_DESC

                            SortType.ALPHABETICAL_DESC ->
                                SortType.ALPHABETICAL_ASC
                        }
                    )
                }

            is CategoryUIEvent.MealClicked -> Unit
        }
    }


    private fun loadMealsByCategory(categoryName: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true, errorMessage = null)
            }

            mealRepository
                .getAllMealsFromCategory(categoryName)
                .collect { result ->
                    result
                        .onSuccess { meals ->
                            _state.update {
                                it.copy(
                                    meals = meals,
                                    isLoading = false,
                                    errorMessage = null
                                )
                            }
                        }
                        .onFailure {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = "Erreur de chargement"
                                )
                            }
                        }
                }
        }
    }

}

sealed interface CategoryUIEvent {

    data class LoadMeals(val categoryName: String) : CategoryUIEvent

    data class SearchChanged(val text: String) : CategoryUIEvent

    object ToggleSort : CategoryUIEvent

    data class MealClicked(val meal: MealPreview) : CategoryUIEvent
}

