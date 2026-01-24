package marina.carbone.tp_note_marina_carbone.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import marina.carbone.tp_note_marina_carbone.domain.model.Category
import kotlin.math.absoluteValue

@Composable
fun CategoryItem(
    category: Category,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .height((200 + category.categoryName.hashCode() % 100).absoluteValue.dp) // hauteur pseudo-unique selon la catégorie
            .clickable { onClick() },
        color = Color.LightGray
    ) {
        Box {

            Image(
                painter = rememberAsyncImagePainter(category.categoryThumb),
                contentDescription = category.categoryName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                        )
                    )
            )

            Text(
                text = category.categoryName,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}
