package marina.carbone.tp_note_marina_carbone.ui.route.home

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
import marina.carbone.tp_note_marina_carbone.ui.theme.greenDarkInt

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onCategoryClick: (Category) -> Unit
) {

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = Color(greenDarkInt)
    ) {

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
                    columns = StaggeredGridCells.Fixed(2), // 2 colonnes, tu peux mettre Adaptive si tu veux
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    items(state.categories) { cat ->
                        CategoryItem(
                            category = cat,
                            onClick = { onCategoryClick(cat) }
                        )
                    }
                }
            }
        }
    }
}
