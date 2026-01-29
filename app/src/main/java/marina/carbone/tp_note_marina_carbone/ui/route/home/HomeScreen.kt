package marina.carbone.tp_note_marina_carbone.ui.route.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import marina.carbone.tp_note_marina_carbone.domain.model.Category
import marina.carbone.tp_note_marina_carbone.ui.component.CategoryItem
import marina.carbone.tp_note_marina_carbone.ui.theme.darkLong

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onCategoryClick: (category: Category) -> Unit
) {

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = Color(darkLong)
    ) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)

        ) {
            Column(
                modifier = Modifier.padding(bottom = 32.dp)
            ) {
                Text("CATEGORIES", style = MaterialTheme.typography.titleLarge)
                Text("Régalez-vous!", style = MaterialTheme.typography.titleMedium)
            }

            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.errorMessage != null) {
                Text(text = state.errorMessage)
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(6.dp)
                ) {
                    items(state.categories) { category ->
                        CategoryItem(
                            category = category,
                            onClick = { onCategoryClick(category) }
                        )
                    }
                }
            }
        }
    }
}
