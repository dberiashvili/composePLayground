package com.composeplayground

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.composeplayground.designsystem.ui.theme.ComposePlayGroundTheme
import com.composeplayground.ui.designsystem.ui.ComponentsScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlayGroundTheme {
                val navController = rememberNavController()
                val goBack: () -> Unit = {
                    finish()

                }
                ComponentsScreen(modifier = Modifier.fillMaxSize(), goBack = goBack)
            }
        }
    }
}
