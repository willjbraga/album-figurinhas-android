// MainActivity.kt
package com.example.albumchampions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.albumchampions.navigation.AppNavigation
import com.example.albumchampions.ui.theme.AlbumChampionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlbumChampionsTheme {
                AppNavigation()
            }
        }
    }
}