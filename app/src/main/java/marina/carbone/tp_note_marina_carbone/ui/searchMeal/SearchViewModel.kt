package marina.carbone.tp_note_marina_carbone.ui.searchMeal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import marina.carbone.tp_note_marina_carbone.data.repository.MealRepositoryImpl
import marina.carbone.tp_note_marina_carbone.domain.repository.MealRepository
import marina.carbone.tp_note_marina_carbone.ui.home.HomeUIEvent

class SearchViewModel(
    private val mealRepository: MealRepository = MealRepositoryImpl()
): ViewModel() {

    private val _state: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())

    val state: StateFlow<SearchState>
        get() = _state.asStateFlow()

    init {
        println("SearchViewModel initialized")
    }

    fun onEvent(event: HomeUIEvent) {
        when(event) {
            is HomeUIEvent.ChangeSearchText -> updateSearchText(event.searchText)
            is HomeUIEvent.SubmitSearch -> {
                println("SubmitSearch clicked, searchText=${_state.value.searchText}")
                search()
            }
        }
    }

    private fun updateSearchText(searchText: String) {
        _state.update {
            it.copy(searchText = searchText)
        }
    }

    private fun search() {
        viewModelScope.launch {
            _state.value.searchText?.let { it ->
                _state.update { state ->
                    state.copy(isLoading = true)
                }
                mealRepository.searchMeals(it).collect { result ->
                    result.fold(
                        onSuccess = { cat ->
                            _state.update { state ->
                                state.copy(meals = cat, isLoading = false)
                            }
                        },
                        onFailure = { error ->
                            _state.update {
                                it.copy(
                                    meals = emptyList(),
                                    isLoading = false,
                                    errorMessage = error.message ?: "Erreur inconnue"
                                )
                            }
                        }
                    )

                }

            }
        }
    }
}

sealed interface SearchUIEvent {
    data class ChangeSearchText(val searchText: String) : SearchUIEvent
    data object SubmitSearch: SearchUIEvent
}
