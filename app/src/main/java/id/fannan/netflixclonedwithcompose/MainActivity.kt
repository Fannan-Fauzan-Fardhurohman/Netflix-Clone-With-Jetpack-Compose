package id.fannan.netflixclonedwithcompose

import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.fannan.netflixclonedwithcompose.ui.MainViewModel
import id.fannan.netflixclonedwithcompose.ui.Routers
import id.fannan.netflixclonedwithcompose.ui.screen.auth.login.LoginScreen
import id.fannan.netflixclonedwithcompose.ui.screen.auth.register.RegisterScreen
import id.fannan.netflixclonedwithcompose.ui.screen.detail.MovieDetailScreen
import id.fannan.netflixclonedwithcompose.ui.screen.home.HomeScreen
import id.fannan.netflixclonedwithcompose.ui.theme.NetflixClonedWithComposeTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                Handler(mainLooper).postDelayed({
                    viewModel.setIsSplash(true)
                }, 3000)
                viewModel.isSplash.value
            }
        }
        super.onCreate(savedInstanceState)
        setContent {
            NetflixClonedWithComposeTheme {
                NetflixCloneApps()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun NetflixCloneApps(
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = MainViewModel.Factory)
) {
    val navController = rememberNavController()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getIsLoggiedInUser()
    }

    isLoggedIn?.let {
        NavHost(
            navController = navController,
            startDestination = if (it) Routers.HOME else Routers.LOGIN
        ) {
            composable(
                route = Routers.LOGIN
            ) {
                LoginScreen(navController)
            }

            composable(
                route = Routers.REGISTER
            ) {
                RegisterScreen(navController)
            }

            composable(
                route = Routers.HOME
            ) {
                HomeScreen(navController)
            }
            composable(
                route = "${Routers.DETAIL}/{movieId}",
                arguments = listOf(
                    navArgument("movieId") { type = NavType.StringType }
                )
            ) { navBackStackEntry ->
                val movieId = navBackStackEntry.arguments?.getString("movieId")
                MovieDetailScreen(movieId.orEmpty(), navController)
            }
        }
    }


}