package com.example.gdsc.view

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gdsc.viewmodel.RetrofitViewModel

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForcastScreen(retrofitViewModel: RetrofitViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        retrofitViewModel.forecastResponse.list?.let { listForecast ->
            LazyRow(
                modifier = Modifier.fillMaxSize()
            ) {
                items(listForecast) { currentItem ->
                    currentItem.let {
                        item ->
                        var temp = ""
                        var icon = ""
                        var time = ""
                        var dt_textt = ""

                        item.main.let { main ->
                            temp = if (main == null) "NA" else "${main.temp}°C"
                        }

                        item.weather.let { weathers ->
                            icon = if (weathers == null) "NA" else weathers.get(0).icon.toString()
                        }

                        item.dt.let { dateTime ->
                            time = if (dateTime == null) "NA" else SimpleDateFormat("HH:mm").format(dateTime.toLong() * 1000)
                        }

                        item.dt_txt.let { dateTime ->
                            dt_textt = if (dateTime == null) "NA" else {
                                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                val outputFormat = SimpleDateFormat("yyyy-MM-dd")
                                val date = inputFormat.parse(dateTime)
                                val calendar = Calendar.getInstance()
                                calendar.time = date
                                calendar.add(Calendar.HOUR_OF_DAY, 6)

                                outputFormat.format(calendar.time)
                            }
                        }

                        ForecastCard(temp = temp, icon = icon, time = time, dt_text = dt_textt)
                    }

                }
            }
        }
    }
}

@Composable
fun ForecastCard(temp : String, icon : String, time : String, dt_text : String) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray.copy(alpha = 0.8f)
        ),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = dt_text)

            AsyncImage(model ="https://openweathermap.org/img/wn/$icon@2x.png" , contentDescription = "")

            Text(text = temp)

            Text(text = time)
        }

    }

}