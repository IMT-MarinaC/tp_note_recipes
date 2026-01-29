import marina.carbone.tp_note_marina_carbone.domain.model.MealPreview

sealed interface CategoryUIEvent {

    data class LoadMeals(val categoryName: String) : CategoryUIEvent

    data class SearchChanged(val text: String) : CategoryUIEvent

    object ToggleSort : CategoryUIEvent

    data class MealClicked(val meal: MealPreview) : CategoryUIEvent
}
