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
}

sealed interface HomeUIEvent {
    data class ChangeSearchText(val searchText: String) : HomeUIEvent
    data object SubmitSearch : HomeUIEvent
}
