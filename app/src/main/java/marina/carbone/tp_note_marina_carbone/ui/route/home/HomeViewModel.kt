package marina.carbone.tp_note_marina_carbone.ui.route.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import marina.carbone.tp_note_marina_carbone.data.repository.CategoryRepositoryImpl
import marina.carbone.tp_note_marina_carbone.domain.repository.CategoryRepository

class HomeViewModel(
    private val categoryRepository: CategoryRepository = CategoryRepositoryImpl()
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    init {
        println("HomeViewModel initialized")
        loadAllCategories()
    }

    /*

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

     */

    private fun loadAllCategories() {
        viewModelScope.launch {
            categoryRepository.getAllCategories().collect { result ->
                result.onSuccess { categories ->
                    _state.update {
                        it.copy(categories = categories, isLoading = false)
                    }
                }.onFailure {
                    _state.update {
                        it.copy(errorMessage = "Erreur de chargement")
                    }
                }
            }
        }
    }

    /*
        private fun search() {
            viewModelScope.launch {
                _state.value.searchText?.let { it ->
                    _state.update { state ->
                        state.copy(isLoading = true)
                    }
                    categoryRepository.findMeal(it).collect { result ->
                        result.fold(
                            onSuccess = { cat ->
                                _state.update { state ->
                                    state.copy(categories = cat, isLoading = false)
                                }
                            },
                            onFailure = { error ->
                                _state.update {
                                    it.copy(
                                        categories = emptyList(),
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

     */


}

sealed interface HomeUIEvent {
    data class ChangeSearchText(val searchText: String) : HomeUIEvent
    data object SubmitSearch : HomeUIEvent
}
