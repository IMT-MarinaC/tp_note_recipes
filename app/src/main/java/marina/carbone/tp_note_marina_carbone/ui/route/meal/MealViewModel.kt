package marina.carbone.tp_note_marina_carbone.ui.route.meal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import marina.carbone.tp_note_marina_carbone.data.repository.MealRepositoryImpl
import marina.carbone.tp_note_marina_carbone.domain.repository.MealRepository

class MealViewModel(
    private val mealRepository: MealRepository = MealRepositoryImpl()
) : ViewModel() {

    private val _meal = MutableStateFlow(MealState())
    val meal: StateFlow<MealState> = _meal.asStateFlow()

    fun loadMealById(mealId: String) {
        viewModelScope.launch {
            _meal.update { it.copy(isLoading = true, errorMessage = null) }

            val result = mealRepository.getMealById(mealId)
            result
                .onSuccess { m ->
                    _meal.update { it.copy(meal = m, isLoading = false, errorMessage = null) }
                }
                .onFailure {
                    _meal.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Erreur de chargement"
                        )
                    }
                }
        }
    }


}