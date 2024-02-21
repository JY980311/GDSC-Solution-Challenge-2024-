package com.example.gdsc

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.gdsc.viewmodel.RetrofitViewModel
import com.example.gdsc.ui.theme.GDSCTheme
import com.example.gdsc.view.BottomSheetScreen
import com.example.gdsc.viewmodel.GoogleMapViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            GDSCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomSheetScreen(viewModel = GoogleMapViewModel(), retrofitViewModel = RetrofitViewModel())
                }
            }
        }
    }
}





/* <Test code>
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting(retrofitViewModel: RetrofitViewModel) {

    fetchWeatherInfo(retrofitViewModel, "Seoul")

    when(retrofitViewModel.state)
        STATE.LOADING -> {
            Text(
                text = "로딩중",
                modifier = Modifier.fillMaxSize()
            )
        }
        STATE.SUCCESS -> {
            Text(
                text = retrofitViewModel.forecastResponse.toString(),
                modifier = Modifier.fillMaxSize()
            )
        }
        STATE.ERROR -> {
            Text(
                text = "로딩실패",
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}

private fun fetchWeatherInfo(retrofitViewModel: RetrofitViewModel, position: String) {
    retrofitViewModel.state = STATE.LOADING
    retrofitViewModel.getWeatherByLocation(position)
    retrofitViewModel.getForcastByLocation(position)
    retrofitViewModel.state = STATE.SUCCESS
}
*/

