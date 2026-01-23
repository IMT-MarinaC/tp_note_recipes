package marina.carbone.tp_note_marina_carbone.ui.route.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import marina.carbone.tp_note_marina_carbone.domain.model.Category
import marina.carbone.tp_note_marina_carbone.ui.component.CategoryItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onEvent: (HomeUIEvent) -> Unit = {},
    onCategoryClick: (Category) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.errorMessage != null) {
            Text(text = state.errorMessage)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.categories) { cat ->
                    CategoryItem(
                        category = cat,
                        onClick = {
                            onCategoryClick(cat)
                        }
                    )
                }
            }
        }
    }
}
