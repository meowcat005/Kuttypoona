package com.lumeo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.lumeo.app.ui.LumeoNavHost
import com.lumeo.app.ui.theme.LumeoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LumeoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LumeoNavHost()
                }
            }
        }
    }
}
