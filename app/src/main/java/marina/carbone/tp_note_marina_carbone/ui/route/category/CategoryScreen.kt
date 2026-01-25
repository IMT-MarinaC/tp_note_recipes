package marina.carbone.tp_note_marina_carbone.ui.route.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import marina.carbone.tp_note_marina_carbone.domain.model.MealPreview
import marina.carbone.tp_note_marina_carbone.ui.component.MealPreviewItem

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    categoryName: String,
    viewModel: CategoryViewModel = viewModel(),
    onMealClick: (MealPreview) -> Unit
) {
    val meals by viewModel.meals.collectAsState()


    LaunchedEffect(categoryName) {
        viewModel.loadMealsByCategory(categoryName)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Horizontal + WindowInsetsSides.Top
                )
            ),
    ) {
        Column {
            Text(
                categoryName,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                "Toutes les recettes",
                style = MaterialTheme.typography.titleMedium
            )
        }

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(2.dp)
        ) {
            items(meals.meals) { meal ->
                MealPreviewItem(
                    meal = meal,
                    onClick = {
                        onMealClick(meal)
                    }
                )
            }
        }

        /*
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(meals.meals) { meal ->
                MealPreviewItem(
                    meal = meal,
                    onClick = {
                        onMealClick(meal)
                    }
                )
            }
        }

         */
    }

}
