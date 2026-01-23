package marina.carbone.tp_note_marina_carbone.ui.searchMeal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import marina.carbone.tp_note_marina_carbone.domain.model.Meal
import marina.carbone.tp_note_marina_carbone.ui.component.MealItem

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    onEvent: (SearchUIEvent) -> Unit = {},
    onCategoryClick: (Meal) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Search Screen")

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = state.searchText ?: "",
            onValueChange = {
                onEvent(SearchUIEvent.ChangeSearchText(it))
            }
        )
        Button(
            modifier = Modifier.fillMaxWidth(0.5F),
            onClick = {
                onEvent(SearchUIEvent.SubmitSearch)
            }
        ) {
            Text(text = "Search")
        }
        Spacer(modifier = Modifier.height(16.dp))



        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.errorMessage != null) {
            Text(text = state.errorMessage)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.meals) { meal ->
                    MealItem(
                        meal = meal,
                        onClick = {
                            onCategoryClick(meal)
                        }
                    )
                }
            }
        }
    }
}
