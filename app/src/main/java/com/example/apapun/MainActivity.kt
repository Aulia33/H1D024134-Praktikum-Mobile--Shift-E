package com.example.apapun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apapun.ui.screen.BasicInfoScreen
import com.example.apapun.ui.screen.HubungiKamiScreen
import com.example.apapun.ui.theme.ApapunTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ApapunTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "basic_info"
                    ) {

                        composable("basic_info") {
                            BasicInfoScreen(
                                onNavigateToContact = {
                                    navController.navigate("form_screen")
                                }
                            )
                        }

                        composable("form_screen") {
                            HubungiKamiScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}