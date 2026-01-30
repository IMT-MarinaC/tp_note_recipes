package marina.carbone.tp_note_marina_carbone.ui.route.meal

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import marina.carbone.tp_note_marina_carbone.ui.component.InfoTag
import marina.carbone.tp_note_marina_carbone.ui.component.IngredientCardItem
import marina.carbone.tp_note_marina_carbone.ui.theme.Dark

@Composable
fun MealScreen(
    mealId: String,
    viewModel: MealViewModel = viewModel(),
    onNavigateBack: () -> Unit
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
                    text = mealState.errorMessage ?: "Erreur inconnue :/",
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
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 72.dp)
            ) {
                item {
                    Text(
                        text = "←",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 32.sp,
                        color = Dark,
                        modifier = Modifier
                            .clickable { onNavigateBack() }
                            .padding(8.dp)
                    )
                }
                item {
                    Box {
                        AsyncImage(
                            model = meal.thumbnail,
                            contentDescription = meal.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = meal.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomStart)
                                .background(
                                    MaterialTheme.colorScheme.background.copy(alpha = 0.6f),
                                )
                                .padding(horizontal = 12.dp, vertical = 16.dp),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }


                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        InfoTag(meal.category)
                        InfoTag(meal.area)
                    }
                }

                item {
                    val context = LocalContext.current
                    val youtubeUrl = meal.youtubeUrl

                    if (!youtubeUrl.isNullOrEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(Dark)
                                .clickable {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                                    context.startActivity(intent)
                                }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Voir la vidéo YouTube",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }


                item {
                    Text(
                        text = "Ingrédients",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                }

                item {
                    val ingredients = meal.ingredients
                    val mid = (ingredients.size + 1) / 2
                    val firstColumn = ingredients.take(mid)
                    val secondColumn = ingredients.drop(mid)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            firstColumn.forEach { IngredientCardItem(it) }
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            secondColumn.forEach { IngredientCardItem(it) }
                        }
                    }
                }

                item {
                    Text(
                        text = "Instructions",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                    Spacer(modifier = Modifier.height(4.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        meal.instructions
                            .replace("\r\n", "\n")
                            .split("\n")
                            .filter { it.isNotBlank() }
                            .forEach { line ->
                                val trimmedLine = line.trim()
                                val isStep = trimmedLine.contains("STEP", ignoreCase = true) ||
                                        trimmedLine.contains("étape", ignoreCase = true)

                                if (isStep) {
                                    Text(
                                        text = trimmedLine,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        modifier = Modifier.padding(vertical = 4.dp)
                                    )
                                } else {
                                    val checked = remember { mutableStateOf(false) }

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(
                                            checked = checked.value,
                                            onCheckedChange = { checked.value = it }
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = trimmedLine,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = if (checked.value) Color.LightGray else MaterialTheme.colorScheme.onBackground
                                        )
                                    }
                                }
                            }
                    }


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
