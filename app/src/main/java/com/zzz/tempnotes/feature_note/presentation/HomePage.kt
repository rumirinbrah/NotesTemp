package com.zzz.tempnotes.feature_note.presentation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zzz.tempnotes.feature_note.domain.model.Note
import com.zzz.tempnotes.feature_note.presentation.components.CreateNoteDialog
import com.zzz.tempnotes.feature_note.presentation.components.NoteDetailPage
import com.zzz.tempnotes.feature_note.presentation.components.NoteItem
import com.zzz.tempnotes.feature_note.presentation.components.VerticalSpace
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    notesViewModel: NotesViewModel = viewModel() ,
) {
    val snackbarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val notes by notesViewModel.notes.collectAsStateWithLifecycle()
    val state by notesViewModel.uiState.collectAsStateWithLifecycle()

    //for toggling details
    var dialogVisible by remember { mutableStateOf(false) }
    var showFullNote by remember { mutableStateOf(false) }
    var currentNote by remember { mutableStateOf<Note?>(null) }


    //for handling back gestures when notes in detail mode
    BackHandler {
        if (showFullNote) {
            showFullNote = false
            currentNote = null
        }
    }
    Box(
        Modifier.fillMaxSize()
    ) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(8.dp) ,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = notes ,
                key = { it.id }
            ) { note ->
                NoteItem(
                    note ,
                    onClick = {
                        showFullNote = true
                        currentNote = it
                    } ,
                    onDelete = {
                        notesViewModel.deleteNote(it)
                        scope.launch {
                            val result = snackbarState.showSnackbar(
                                message = "Note deleted" ,
                                actionLabel = "Undo"
                            )
                            when(result){
                                SnackbarResult.Dismissed -> {
                                    notesViewModel.clearLastDeleted()
                                }
                                SnackbarResult.ActionPerformed -> {
                                    notesViewModel.undoDelete()
                                }
                            }
                        }
                    } ,
                    modifier = Modifier.animateItem()
                )

            }
        }

        //add fab
        FloatingActionButton(
            onClick = {
                dialogVisible = true
            } ,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add , contentDescription = null)
        }

        //detailed view
        if (dialogVisible) {
            CreateNoteDialog(
                state = state ,
                onTitleChange = {
                    notesViewModel.onTitleChange(it)
                } ,
                onBodyChange = {
                    notesViewModel.onBodyChange(it)
                } ,
                onSave = {
                    notesViewModel.addNote()
                    dialogVisible = false
                } ,
                onDismiss = {
                    dialogVisible = false
                    notesViewModel.clearState()
                }

            )
        }
        AnimatedVisibility(showFullNote) {
            NoteDetailPage(
                currentNote ,
                onDismiss = {
                    showFullNote = false
                    currentNote = null
                }
            )
        }
        SnackbarHost(snackbarState , modifier = Modifier.align(Alignment.BottomCenter))

    }

}