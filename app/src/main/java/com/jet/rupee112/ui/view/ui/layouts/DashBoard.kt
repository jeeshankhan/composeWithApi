package com.jet.rupee112.ui.view.ui.layouts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.annotations.ApiStatus.Experimental

@Composable
fun DashBoard(){

}

@Preview()
@Composable
fun ImageSlider(){
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        items(15) {i->
            Text(text = "item $i", color = Color.Green)
        }
    }
}




@Composable
fun ImageSlider(images: List<String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(images.size) { index ->
            val imageModel = images[index]
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .width(300.dp)
            ) {
//                Image(
//                    painter = rememberImagePainter(data = imageModel.imageUrl),
//                    contentDescription = "",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .height(200.dp)
//                        .fillMaxWidth()
//                        .clip(RoundedCornerShape(8.dp))
//                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun IndicatorDot(
    modifier: Modifier=Modifier,
    size:Dp=10.dp,
    color: Color= Color.Green
){
    Box(modifier = modifier
        .size(size)
        .clip(CircleShape)
        .background(color)
    )
}



@OptIn(ExperimentalFoundationApi::class)
@Preview
@Experimental
@Composable
fun ImageSlider2() {
    val images = listOf(
        com.jet.rupee112.R.drawable.img1,
        com.jet.rupee112.R.drawable.img2,
        com.jet.rupee112.R.drawable.img3,
        com.jet.rupee112.R.drawable.img4,
        com.jet.rupee112.R.drawable.img5,
        com.jet.rupee112.R.drawable.img6,
    )

    val pagerState = rememberPagerState { 10 }
    HorizontalPager(state = pagerState) { page ->



        Image(painter = painterResource(id = images[page]),
            contentDescription = "some",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(180f)
        )
//                    Text(
//            text = "Page number $page",
//            modifier = Modifier.fillMaxWidth(),
//            color = Color.Black,
//            fontSize = TextUnit.Unspecified
//        )

        Button(onClick = { /*TODO*/ }
        ) {
            Text(text = "Next")
        }
    }


//    var currentIndex by remember { mutableStateOf(0) }
//    val coroutineScope = rememberCoroutineScope()

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Gray)
//    ) {
//        LazyRow(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            items(images.size) { index ->
//                Image(
//                    painter = painterResource(id = 1),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .size(300.dp)
//                        .clip(RoundedCornerShape(16.dp)),
//                    contentScale = ContentScale.Crop
//                )
//            }
//        }
//    }
}

