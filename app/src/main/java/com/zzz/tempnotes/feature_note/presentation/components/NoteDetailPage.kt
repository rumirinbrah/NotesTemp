package com.zzz.tempnotes.feature_note.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zzz.tempnotes.feature_note.domain.model.Note

@Composable
fun NoteDetailPage(
    note: Note?,
    onDismiss :()->Unit,
    modifier: Modifier = Modifier
) {
    Column(
        Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            Modifier.clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    onDismiss()
                }
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ){
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
        VerticalSpace()
        Text(
            note?.title ?: "" ,
            fontSize = 20.sp ,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.ExtraBold
        )
        Text(note?.body ?:"", fontSize = 16.sp, fontWeight = FontWeight.Normal)
    }
}