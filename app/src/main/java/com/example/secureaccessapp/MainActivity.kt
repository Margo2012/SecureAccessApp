package com.example.secureaccessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.secureaccessapp.data.AccessManager
import com.example.secureaccessapp.data.User
import com.example.secureaccessapp.delegates.SecureDelegate
import com.example.secureaccessapp.ui.theme.SecureAccessAppTheme


class MainActivity : ComponentActivity() {
    //private val manager = User("Ivan", 4)
    private val worker = User("Vasilii", 1)
    //private val access = AccessManager(manager)
    private val access2 = AccessManager(worker)

    private var confidentialInfo: String? by SecureDelegate("Secret data") { access2.hasAccess(2) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecureAccessAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }


    @Composable
    fun MainScreen(modifier: Modifier) {
        var message by remember { mutableStateOf("") }
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            )
        {
            Text(text = "Hello, ${worker.name}!")

            //Если при доступе к работнику, у него недостаточный уровень доступа
            // и мы выкидваем исключение, то следующий код необходимо обернуть в try-catch
            if (confidentialInfo != null) {
                Text(text = "Access to confidential information: $confidentialInfo")
            } else {
                Text(text = "Access to confidential information is prohibited.")
            }


            Button(onClick = {
                try {
                    confidentialInfo = "New Secret Data"
                    message = "Info has been updated successfully."
                } catch (e: IllegalAccessException) {
                    message = e.message ?: "Access error"
                }
            }) {
                Text("Try update information")
            }

            Text(text = message)
        }
    }
}


