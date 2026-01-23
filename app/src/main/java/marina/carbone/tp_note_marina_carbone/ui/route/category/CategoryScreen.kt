package marina.carbone.tp_note_marina_carbone.ui.route.category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import marina.carbone.tp_note_marina_carbone.domain.model.MealPreview
import marina.carbone.tp_note_marina_carbone.ui.component.MealPreviewItem

@Composable
fun CategoryScreen(
    categoryName: String,
    viewModel: CategoryViewModel = viewModel(),
    onMealClick: (MealPreview) -> Unit
) {
    val meals by viewModel.meals.collectAsState()

    LaunchedEffect(categoryName) {
        viewModel.loadMealsByCategory(categoryName)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = categoryName,
                fontSize = 28.sp
            )
        }

        items(meals.meals) { meal ->
            MealPreviewItem(
                meal = meal,
                onClick = {
                    onMealClick(meal)
                }
            )
        }
    }
}
