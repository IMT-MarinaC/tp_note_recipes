package marina.carbone.tp_note_marina_carbone.ui.route.meal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MealScreen(
    mealId: String,
    viewModel: MealViewModel = viewModel()
) {
    val mealState by viewModel.meal.collectAsState()

    LaunchedEffect(mealId) {
        viewModel.loadMealById(mealId)
    }

    when {
        mealState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        mealState.errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = mealState.errorMessage ?: "Erreur inconnue",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        mealState.meal != null -> {
            val meal = mealState.meal ?: return

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = meal.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 28.sp
                    )
                }

                item {
                    Text(
                        text = "Instructions",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = meal.instructions,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                item {
                    Text(
                        text = "Ingrédients",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                items(meal.ingredients) { ingredient ->
                    Text(
                        text = "${ingredient.name} - ${ingredient.measure}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Aucune donnée")
            }
        }
    }
}
