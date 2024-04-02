package com.jet.rupee112

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jet.rupee112.api.ApiController
import com.jet.rupee112.api.ApiService
import com.jet.rupee112.api.DataState
import com.jet.rupee112.api.apiService
import com.jet.rupee112.modal.AppVersionRequest
import com.jet.rupee112.ui.theme.Rupee112Theme
import com.jet.rupee112.ui.version.VersionViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Rupee112Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MobileNumberEnterScreen()
//                    val apiService = rememberSaveable {
//                        ApiController.getApi()
//                    }
                    VersionCheck()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val rainbowColors = listOf(
        Color.Red,
        Color(0xFFFF7F00), // Orange
        Color.Yellow,
        Color.Green,
        Color.Blue,
        Color(0xFF4B0082), // Indigo
        Color(0xFF8A2BE2)  // Violet
    )



    var text by rememberSaveable{ mutableStateOf("") }
    val brush  = remember {
        Brush.linearGradient(
            colors = rainbowColors
        )
    }

    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(horizontal = 12.dp)
    ) {
        Column( modifier= Modifier
            .padding(10.dp)
            .background(
                colorResource(id = R.color.transparent_Blue),
                shape = MaterialTheme.shapes.medium
            )
            .padding(10.dp)
        ) {

            Text(
                text = "Hello $name!",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            )

            TextField(
                value = text,
                modifier = modifier
                    .fillMaxWidth(),
                onValueChange ={nt-> text=nt.trimStart { it=='0' }},
                label = { Text(text = "Mobile Number")},
                maxLines = 1,
                textStyle = TextStyle(fontWeight = FontWeight.Bold, brush = brush),
//    visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(modifier =modifier.align(Alignment.End) ,onClick = {

                val inputTex = text
                text = "jeeshan"

            },) {
                Text(text = "Click")
            }
            Spacer(modifier = modifier.size(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MobileNumberEnterScreen(modifier: Modifier=Modifier){
    Rupee112Theme() {
        val gradientColors =listOf(
            Color.LightGray,
            colorResource(id = R.color.transparent_Blue),
            Color.Cyan,
            // Violet
        )
        // Create a Brush using the gradient colors
        val gradientBrush = Brush.verticalGradient(gradientColors)

        // Draw the gradient background using DrawScope


        Box(modifier = modifier
            .fillMaxSize()
            .paint(painter = painterResource(id = R.drawable.img_refer))){
//    Surface( modifier = modifier
//        .fillMaxSize()
//        .paint(painter = painterResource(id = R.drawable.img_refer))
//
//    ) {
            Surface( modifier = modifier
                .fillMaxSize(),
                color = colorResource(id = R.color.transparent_Blue),

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = modifier.background(Brush.verticalGradient(gradientColors))
                ) {
                    Greeting(name = "Enter Mobile Number")
                }
            }
//    }
        }
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileCard(modifier: Modifier =Modifier){
    var text by rememberSaveable{
        mutableStateOf(0)
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Yellow)
    ) {
        Text(modifier = modifier,text = "Enter Your mobile number")
        TextField(value ="", onValueChange ={} )
    }
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Rupee112Theme {
        Greeting("Android")
    }
}


@Composable
fun VersionCheck() {
    val  viewModel: VersionViewModel = viewModel()
    viewModel.checkVersionApp(ApiController.getApi(), AppVersionRequest("BL",1))
    Log.d("tag","start")

    val dataState by viewModel.versionCheck.collectAsState()
    when (dataState) {
        is DataState.Loading -> {
            // Show loading indicator
            Log.d("tag","loading")
        }
        is DataState.Success -> {
            val data = (dataState as DataState.Success).data
            Log.d("tag","Success")
            // Show data
        }
        is DataState.Error -> {
            Log.d("tag","Error")
            val message = (dataState as DataState.Error).message
            // Show error message
        }
    }

    // Trigger API call on initial composition
}