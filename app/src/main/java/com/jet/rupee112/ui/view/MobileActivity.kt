package com.jet.rupee112.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jet.rupee112.ui.view.ui.theme.Rupee112Theme

class MobileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Rupee112Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenMobile(){
    ConstraintLayout {
        val (a,b) = createRefs()


        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(a){
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
            ) {
            Text(text = "Get Otp")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Rupee112Theme {
        Greeting2("Android")
    }
}