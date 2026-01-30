package marina.carbone.tp_note_marina_carbone.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import marina.carbone.tp_note_marina_carbone.ui.theme.GreenDark
import marina.carbone.tp_note_marina_carbone.ui.theme.White

@Composable
fun InfoTag(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .background(
                color = GreenDark,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        style = MaterialTheme.typography.labelMedium,
        color = White
    )
}
