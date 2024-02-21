package com.example.gdsc.view


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gdsc.viewmodel.GoogleMapViewModel
import com.example.gdsc.viewmodel.ReadFireBaseViewModel
import com.example.gdsc.viewmodel.RetrofitViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(viewModel: GoogleMapViewModel, retrofitViewModel: RetrofitViewModel) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        sheetContent = { BottomSheetContent(viewModel, retrofitViewModel = RetrofitViewModel(), readFireBaseViewModel = ReadFireBaseViewModel()) },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 128.dp,
        sheetSwipeEnabled = true,
        sheetShape = RoundedCornerShape(topEnd = 16.dp , topStart = 16.dp),
        sheetContainerColor = Color.White,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {
            Button(
                modifier = Modifier.padding(80.dp),
                onClick = { scope.launch { scaffoldState.bottomSheetState.expand() } }
            ) {
                Text(text = "Expand")
            }
            GoogleMapScreen(
                modifier = Modifier.fillMaxSize(),
                click = { scope.launch { scaffoldState.bottomSheetState.expand() } },
                viewModel = viewModel,
                retrofitViewModel = retrofitViewModel
            )
        }
    }
}

