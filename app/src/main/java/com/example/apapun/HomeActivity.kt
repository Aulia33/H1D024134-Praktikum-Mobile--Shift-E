package com.example.apapun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.apapun.ui.screen.DaftarProductScreen
import com.example.apapun.ui.theme.ApapunTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ApapunTheme {
                DaftarProductScreen()
            }
        }
    }
}