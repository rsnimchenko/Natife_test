package com.app.natifetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.natifetest.ui.NavigationComponent
import com.app.natifetest.ui.theme.NatifeTestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NatifeTestTheme { NavigationComponent() }
        }
    }
}