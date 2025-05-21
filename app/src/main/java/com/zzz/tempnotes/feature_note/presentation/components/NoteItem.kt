package com.zzz.tempnotes.feature_note.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.zzz.tempnotes.feature_note.domain.model.Note

@Composable
fun NoteItem(
    note : Note,
    onClick : (Note)->Unit,
    onDelete : (Note)->Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth()
            .clip(Shapes().medium)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable {
                onClick(note)
            }
            .padding(16.dp) ,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            Modifier.weight(1f)
        ) {
            Text(
                note.title ,
                fontSize = 20.sp ,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                note.body ,
                fontSize = 16.sp ,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }
        IconButton(
            onClick = {
                onDelete(note)
            }
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }

    }
}