package com.user.weatherapp.ui.splashPage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.user.weatherapp.R
import com.user.weatherapp.data.local.AppOpenStatus
import com.user.weatherapp.ui.navigation.PageRoutes
import com.user.weatherapp.ui.weather.components.Animation
import com.user.weatherapp.utils.SPLASH_WAIT_TIME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController,
                 viewModel: SplashViewModel = hiltViewModel()) {
    val isFirstTimeFlow = viewModel.appOpenStatus.collectAsState(initial = AppOpenStatus.UNKNOWN)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(isFirstTimeFlow.value) {
        if (isFirstTimeFlow.value == AppOpenStatus.FIRST_TIMER ||
            isFirstTimeFlow.value == AppOpenStatus.UNKNOWN) {
            //delay for first-time users
            delay(SPLASH_WAIT_TIME)
        }
        navigateToMainScreen(navController, viewModel, coroutineScope)

    }

    if (isFirstTimeFlow.value == AppOpenStatus.FIRST_TIMER){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp)
                        .testTag("Welcome message"),
                    style = MaterialTheme.typography.titleMedium,
                    text = stringResource(R.string.splash_message),
                    fontWeight = FontWeight.Medium, fontSize = 24.sp)
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    navigateToMainScreen(navController, viewModel, coroutineScope)
                }) {
                    Text(stringResource(R.string.splash_button))
                }
            }
        }
    } else {
        //if user status not known due to delay at retrieval, then display progress
        Animation(modifier = Modifier.fillMaxSize(), animation = R.raw.animation_loading)
    }

}

fun navigateToMainScreen(
    navController: NavController,
    viewModel: SplashViewModel,
    coroutineScope: CoroutineScope
) {
    coroutineScope.launch {
        // Mark that the user has opened the app
        viewModel.updateAppOpenStatus(AppOpenStatus.NON_FIRST_TIMER)
        navController.navigate(PageRoutes.HOME.name) {
            popUpTo(PageRoutes.WELCOME.name) { inclusive = true }
        }
    }
}