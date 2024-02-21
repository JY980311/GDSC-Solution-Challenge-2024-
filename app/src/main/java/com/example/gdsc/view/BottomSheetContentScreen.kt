package com.example.gdsc.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.gdsc.R
import com.example.gdsc.viewmodel.GoogleMapViewModel
import com.example.gdsc.viewmodel.ReadFireBaseViewModel
import com.example.gdsc.viewmodel.RetrofitViewModel
import com.example.gdsc.viewmodel.STATE
import java.text.SimpleDateFormat

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState", "SimpleDateFormat")
@Composable
fun BottomSheetContent(
    viewModel: GoogleMapViewModel,
    retrofitViewModel: RetrofitViewModel,
    readFireBaseViewModel: ReadFireBaseViewModel
) {

    val position = viewModel.selectedCourse // 선택된 위치를 가져오는 코드
    val weatherIcon =
        retrofitViewModel.weatherResponse.weather?.getOrNull(0)?.icon // 날씨 아이콘을 가져오는 코드
    val sdf = SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()) // 현재 시간을 가져오는 코드

    Box(
        Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = viewModel.selectedCourse,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = sdf,
                color = Color.Black,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(20.dp))

            LaunchedEffect(position) {
                fetchWeatherInfo(retrofitViewModel, position)
                readFireBaseViewModel.getCourses(position)
            }

            Text(
                text = "Today's Weather",
                color = Color.Black,
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            when (retrofitViewModel.state) {
                STATE.LOADING -> {
                    Text(
                        text = "로딩중"
                    )
                }

                STATE.SUCCESS -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (weatherIcon != null) {
                            val imageUrl = "https://openweathermap.org/img/wn/${weatherIcon}@2x.png"
                            val painter = rememberImagePainter(
                                data = imageUrl,
                                builder = {
                                    crossfade(true)
                                }
                            )
                            Image(
                                painter = painter,
                                contentDescription = "",
                                modifier = Modifier.size(150.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Temp : " + retrofitViewModel.weatherResponse.main?.temp.toString() + "°C",
                                color = Color.Black,
                                fontSize = 20.sp
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "Wind Speed : " + retrofitViewModel.weatherResponse.wind?.speed.toString() + "m/s",
                                color = Color.Black,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Max Temp : " + retrofitViewModel.weatherResponse.main?.temp_max.toString() + "°C",
                                color = Color.Black,
                                fontSize = 20.sp
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "Min Temp : " + retrofitViewModel.weatherResponse.main?.temp_min.toString() + "°C",
                                color = Color.Black,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
                STATE.ERROR -> {
                    Text(
                        text = "로딩실패" + retrofitViewModel.errorMessage
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            ColoredImage(readFireBaseViewModel.waterQ.value.toFloat())

            Text(
                text = "Water Quality = ${readFireBaseViewModel.waterQ.value}%",
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "News",
                color = Color.Black,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold
            )

            GoogleSearchText(position)

            ForcastScreen(retrofitViewModel)
        }
    }
}

@Composable
fun ColoredImage(value: Float) {
    // value는 0과 100 사이의 값
    val adjustedValue = value.coerceIn(0f, 100f) / 100f

    // 색상을 파란색에서 검정색으로 보간
    val color = lerp(Color.Gray, Color.Blue, adjustedValue)

    Image(
        modifier = Modifier.size(200.dp),
        painter = painterResource(id = R.drawable.water2),
        contentDescription = "",
        colorFilter = ColorFilter.tint(color),
    )
}

@Composable
fun GoogleSearchText(text: String) {
    val topicText = text

    val context = LocalContext.current

    val annotatedText = buildAnnotatedString {
        append("$topicText News")
        addStyle(
            style = SpanStyle(textDecoration = TextDecoration.Underline),
            start = 0,
            end = topicText.length + 5
        )
    }

    ClickableText(
        text = annotatedText,
        onClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/search?q=$text&tbm=nws")
            )
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        },
        style = TextStyle(fontSize = 30.sp)
    )
}


private fun fetchWeatherInfo(retrofitViewModel: RetrofitViewModel, position: String) {
    retrofitViewModel.state = STATE.LOADING
    retrofitViewModel.getWeatherByLocation(position)
    retrofitViewModel.getForcastByLocation(position)
    retrofitViewModel.state = STATE.SUCCESS
}

