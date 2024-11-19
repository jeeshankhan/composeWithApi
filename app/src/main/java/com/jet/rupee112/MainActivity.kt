package com.jet.rupee112

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jet.rupee112.api.ApiController
import com.jet.rupee112.api.DataState
import com.jet.rupee112.modal.AppVersionRequest
import com.jet.rupee112.ui.theme.Rupee112Theme
import com.jet.rupee112.ui.version.VersionViewModel
import com.jet.rupee112.ui.view.MobileActivity


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Rupee112Theme {
                val navController = rememberNavController()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    VersionCheck()
//                }
                Scaffold(
                    topBar = {
                        ToolBar(
                            onClickNextScreen = {
                                navController.navigate("home2")
                            }
                        )
                    },

                    ) {  innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable("home"){
                            MobileNumberEnterScreen()
                        }
                        composable("home2"){
                            BottomMobile()
                        }

                    }
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
        shape = MaterialTheme.shapes.large,
        modifier = modifier.padding(horizontal = 8.dp)
    ) {

//        Button(modifier =modifier.align(Alignment.End) ,onClick = {
//
//            val inputTex = text
//            text = "jeeshan"
//
//        },) {
//            Text(text = "Click")
//        }


        Column( modifier= Modifier

            .background(
                colorResource(id = R.color.app_Color),
                shape = MaterialTheme.shapes.medium
            )
            .padding(18.dp)
        ) {

            Text(
                text = "To Register or Log an account",
                fontStyle = FontStyle.Italic,

                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                color = colorResource(id = R.color.white)
            )

            Text(
                text = "Please enter your mobile number",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                color = colorResource(id = R.color.white)
            )

            TextField(
                value = text,
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.img_refer), contentDescription ="", modifier = Modifier
                    .size(50.dp)
                    .padding(end = 3.dp, start = 5.dp) )}
                ,
                modifier = modifier
                    .padding(horizontal = 8.dp)
                    .background(Color.White, shape = MaterialTheme.shapes.medium)
                    .fillMaxWidth(),
                onValueChange ={nt-> text=nt.trimStart { it=='0' }},
                label = { Text(text = "Mobile Number")},
                maxLines = 1,
                textStyle = TextStyle(fontWeight = FontWeight.Bold, brush = brush),
//    visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = modifier.size(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomMobile(isChecked:Boolean=false){
    var isChecked by rememberSaveable {
        mutableStateOf(false)
    }
    ConstraintLayout {
        val (a,b) = createRefs()

        Row(
            modifier = Modifier
                .constrainAs(b) {
                    bottom.linkTo(a.top)
                }
                .padding(16.dp)
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {isChecked =it}
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Please Select it for further use",
                color = Color.White
            )
        }

        val contex = LocalContext.current
        Button(
            onClick = {
                if (isChecked){
                    Toast.makeText(contex,"checked",Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(contex,"Unchecked",Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .constrainAs(a) {
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                }
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.app_Color)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Get Otp")
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
            .paint(painter = painterResource(id = R.drawable.ic_launcher_background))
        ){


//    Surface( modifier = modifier
//        .fillMaxSize()
//        .paint(painter = painterResource(id = R.drawable.img_refer))
//
//    ) {
            Surface(
                modifier = modifier
                    .fillMaxSize(),
//                color = colorResource(id = R.color.transparent_Blue),

            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_comman),
                    contentDescription ="jhj",
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.FillBounds
                )


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.background(color = colorResource(id = R.color.transparent_Blue))
//                    modifier = modifier.background(Brush.verticalGradient(gradientColors))
                ) {

                    Text(text = " ")
                    Greeting(name = "Enter Mobile Number")
                    BottomMobile()

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
fun VersionCheck(modifier: Modifier=Modifier) {
    val  viewModel: VersionViewModel = viewModel()
    viewModel.checkVersionApp(ApiController.getApi(), AppVersionRequest("BL",1))
    Log.d("tag","start")

    val dataState by viewModel.versionCheck.collectAsState()
    when (dataState) {
        is DataState.Loading -> {
            // Show loading indicator
            CircularProgressIndicator(modifier = Modifier.size(40.dp))
            Log.d("tag","loading")
        }
        is DataState.Success -> {
            MobileNumberEnterScreen()
            val data = (dataState as DataState.Success).data
            Log.d("tag","Success")
            // Show data
        }
        is DataState.Error -> {
            Log.d("tag","Error")
            val message = (dataState as DataState.Error).message
            // Show error message
        }
        else -> {

        }
    }
}



@Composable
fun ToolBar(
    onClickNextScreen:()->Unit = {}
){
    Row {
        Button(onClick =  onClickNextScreen) {
            Text(text = "OnClick")
        }

    }
}