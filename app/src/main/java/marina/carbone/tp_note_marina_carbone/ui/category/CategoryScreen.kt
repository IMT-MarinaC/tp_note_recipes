package marina.carbone.tp_note_marina_carbone.ui.category_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import marina.carbone.tp_note_marina_carbone.ui.category.CategoryViewModel

@Composable
fun CategoryScreen(
    categoryName: String,
    viewModel: CategoryViewModel = viewModel()
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
            Text(
                text = meal.name,
                fontSize = 20.sp
            )
        }
    }
}
