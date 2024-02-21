package com.example.gdsc.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gdsc.viewmodel.GoogleMapViewModel
import com.example.gdsc.viewmodel.RetrofitViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnrememberedMutableState")
@Composable
fun GoogleMapScreen(modifier: Modifier, click: () -> Unit = {}, viewModel: GoogleMapViewModel, retrofitViewModel: RetrofitViewModel) {
    val seoul = LatLng(37.566535, 126.97796919)
    val tokyo = LatLng(35.6894, 139.692)
    val beijing = LatLng(39.9035, 116.388)
    val washington = LatLng(38.9041, -77.0171)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(seoul, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = seoul),
            title = "Seoul",
            snippet = "More Information",
            onInfoWindowClick = {
                click()
                viewModel.onSelectedIndexChange2(1)
            },
            onClick = {
                //click()
                false
            }
        )
        //도쿄
        Marker(
            state = MarkerState(position = tokyo),
            title = "Tokyo",
            snippet = "More Information",
            onInfoWindowClick = {
                click()
                viewModel.onSelectedIndexChange2(2)
            },
            onClick = {
                //click()
                false
            }

        )
        //베이징
        Marker(
            state = MarkerState(position = beijing),
            title = "Beijing",
            snippet = "More Information",
            onInfoWindowClick = {
                click()
                viewModel.onSelectedIndexChange2(3)
            },
            onClick = {
                //click()
                false
            }

        )
        //워싱턴
        Marker(
            state = MarkerState(position = washington),
            title = "Washington",
            snippet = "More Information",
            onInfoWindowClick = {
                click()
                viewModel.onSelectedIndexChange2(4)
            },
            onClick = {
                //click()
                false
            }
        )
    }
}
