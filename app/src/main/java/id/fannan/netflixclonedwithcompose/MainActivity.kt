package id.fannan.netflixclonedwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.fannan.netflixclonedwithcompose.ui.Routers
import id.fannan.netflixclonedwithcompose.ui.screen.detail.MovieDetailScreen
import id.fannan.netflixclonedwithcompose.ui.screen.home.HomeScreen
import id.fannan.netflixclonedwithcompose.ui.theme.NetflixClonedWithComposeTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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
fun NetflixCloneApps() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routers.HOME) {
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