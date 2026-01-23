package marina.carbone.tp_note_marina_carbone.ui.route.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import marina.carbone.tp_note_marina_carbone.data.repository.MealRepositoryImpl
import marina.carbone.tp_note_marina_carbone.domain.repository.MealRepository

class CategoryViewModel(
    private val mealRepository: MealRepository = MealRepositoryImpl(),
) : ViewModel() {

    private val _meals = MutableStateFlow(CategoryState())
    val meals: StateFlow<CategoryState> = _meals.asStateFlow()

    fun loadMealsByCategory(categoryName: String) {
        viewModelScope.launch {
            _meals.update {
                it.copy(isLoading = true, errorMessage = null)
            }

            mealRepository
                .getAllMealsFromCategory(categoryName)
                .collect { result ->
                    result
                        .onSuccess { meals ->
                            _meals.update {
                                it.copy(
                                    meals = meals,
                                    isLoading = false,
                                    errorMessage = null
                                )
                            }
                        }
                        .onFailure {
                            _meals.update {
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
