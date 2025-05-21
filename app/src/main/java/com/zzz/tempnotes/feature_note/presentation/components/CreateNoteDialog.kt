package com.zzz.tempnotes.feature_note.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.zzz.tempnotes.feature_note.presentation.NoteUIState

@Composable
fun CreateNoteDialog(
    state : NoteUIState,
    onTitleChange : (String)->Unit,
    onBodyChange : (String)->Unit,
    onSave : ()->Unit,
    onDismiss : ()->Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Column(
            Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface, shape = Shapes().large)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "Create new note" ,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            VerticalSpace()
            TextField(
                value = state.title ,
                onValueChange = {
                    onTitleChange(it)
                },

                placeholder = {
                    Text("Title")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                singleLine = true

            )
            TextField(
                value = state.body ,
                onValueChange = {
                    onBodyChange(it)
                },
                placeholder = {
                    Text("Body")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
            )
            Spacer(Modifier.height(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )

                ) {
                    Text("Cancel")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onSave

                ) {
                    Text("Save")
                }
            }

        }
    }
}