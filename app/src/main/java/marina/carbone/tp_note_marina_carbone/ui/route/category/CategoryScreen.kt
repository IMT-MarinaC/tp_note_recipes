package marina.carbone.tp_note_marina_carbone.ui.route.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import marina.carbone.tp_note_marina_carbone.domain.model.MealPreview
import marina.carbone.tp_note_marina_carbone.ui.component.MealPreviewItem
import marina.carbone.tp_note_marina_carbone.ui.theme.Dark
import marina.carbone.tp_note_marina_carbone.ui.theme.LightDark
import marina.carbone.tp_note_marina_carbone.ui.theme.White
import marina.carbone.tp_note_marina_carbone.ui.theme.greenDarkLong

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    categoryName: String,
    viewModel: CategoryViewModel = viewModel(),
    onMealClick: (mealPreview: MealPreview) -> Unit,
    onNavigateBack: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    val filteredMeals = remember(state) {
        state.meals
            .filter {
                it.name.contains(state.searchText, true)
            }
            .let { list ->
                when (state.sortType) {
                    SortType.ALPHABETICAL_ASC -> list.sortedBy { it.name }
                    SortType.ALPHABETICAL_DESC -> list.sortedByDescending { it.name }
                }
            }
    }


    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = Dark
    ) {


        LaunchedEffect(categoryName) {
            viewModel.sendUIEvent(
                CategoryUIEvent.LoadMeals(categoryName)
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal + WindowInsetsSides.Top
                    )
                ),
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "←",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier
                            .clickable { onNavigateBack() }
                            .padding(8.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        categoryName,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Text(
                    "Toutes les recettes (${filteredMeals.size})",
                    style = MaterialTheme.typography.titleMedium
                )

            }

            TextField(
                value = state.searchText,
                onValueChange = {
                    viewModel.sendUIEvent(
                        CategoryUIEvent.SearchChanged(it)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                placeholder = {
                    Text(
                        "Rechercher une recette...",
                        color = Dark
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = LightDark,

                    focusedTextColor = Dark,
                    unfocusedTextColor = White,
                )
            )

            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Filtre : ",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )

                Button(
                    onClick = {
                        viewModel.sendUIEvent(CategoryUIEvent.ToggleSort)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(greenDarkLong)
                    )
                ) {
                    Text(
                        text = if (state.sortType == SortType.ALPHABETICAL_ASC)
                            "A → Z"
                        else
                            "Z → A"
                    )
                }
            }

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
            ) {
                items(filteredMeals) { meal ->
                    MealPreviewItem(
                        meal = meal,
                        onClick = {
                            onMealClick(meal)
                        }
                    )
                }
            }
        }
    }

}
