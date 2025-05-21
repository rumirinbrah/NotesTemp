package com.zzz.tempnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.zzz.tempnotes.feature_note.presentation.HomePage
import com.zzz.tempnotes.ui.theme.TempNotesTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TempNotesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize() ,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Notes App", fontWeight = FontWeight.ExtraBold)
                            }
                        )
                    }
                ) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        HomePage()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String , modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!" ,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TempNotesTheme {
        Greeting("Android")
    }
}